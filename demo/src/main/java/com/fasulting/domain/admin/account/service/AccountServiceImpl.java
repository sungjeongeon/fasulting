package com.fasulting.domain.admin.account.service;

import com.fasulting.domain.admin.account.dto.reqDto.ConfirmPsReqDto;
import com.fasulting.domain.admin.account.dto.respDto.PsWaitRespDto;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.repository.category.MainCategoryRepository;
import com.fasulting.repository.category.SubCategoryRepository;
import com.fasulting.repository.ps.PsMainRepository;
import com.fasulting.repository.ps.PsMainSubRepository;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.role.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    private final PsRepository psRepository;
    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;
    private final RoleRepository roleRepository;

    public AccountServiceImpl(PsRepository psRepository, MainCategoryRepository mainCategoryRepository,
                              SubCategoryRepository subCategoryRepository, PsMainRepository psMainRepository,
                              PsMainSubRepository psMainSubRepository, RoleRepository roleRepository) {
        this.psRepository = psRepository;
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public List<PsWaitRespDto> getPsWaitList() {
        List<PsEntity> psList = psRepository.findAllByConfirmYn("N");

        List<PsWaitRespDto> psWaitList = new ArrayList<>();
        for(PsEntity ps : psList) {
            PsWaitRespDto psWait = PsWaitRespDto.builder()
                    .psSeq(ps.getSeq())
                    .email(ps.getEmail())
                    .name(ps.getName())
                    .address(ps.getAddress())
                    .zipcode(ps.getZipcode())
                    .registration(ps.getRegistration())
                    .number(ps.getNumber())
                    .director(ps.getDirector())
                    .homepage(ps.getHomepage())
                    .intro(ps.getIntro())
                    .mainCategoryList(psMainRepository.getMainNameByPsSeq(ps.getSeq()))
                    .subCategoryList(psMainSubRepository.getSubNameByPsSeq(ps.getSeq()))
                    .build();

            psWaitList.add(psWait);
        }

        return psWaitList;
    }

    @Transactional
    @Override
    public boolean approvePs(ConfirmPsReqDto confirmPsReqDto) {

        if(!psRepository.findById(confirmPsReqDto.getPsSeq()).isPresent() &&
                !roleRepository.findById(confirmPsReqDto.getAdminSeq()).isPresent() &&
                !"admin".equals(roleRepository.findById(confirmPsReqDto.getAdminSeq()).get().getAuthority())){
            return false;
        }
        PsEntity ps = psRepository.findById(confirmPsReqDto.getPsSeq()).get();

        ps.updateByConfirm("admin_" + confirmPsReqDto.getAdminSeq(), LocalDateTime.now());

        return true;
    }


}
