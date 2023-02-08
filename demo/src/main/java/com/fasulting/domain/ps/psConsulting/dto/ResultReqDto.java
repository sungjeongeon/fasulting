package com.fasulting.domain.ps.psConsulting.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ResultReqDto {

    Long consultingSeq;
    Long psSeq;
    Long userSeq;

    String reportContent;
    String reportEstimate;

    MultipartFile afterImg;

}