package com.fasulting.domain.jwt.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.CookieUtil;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.UserLoginRespDto;
import com.fasulting.domain.jwt.service.UserJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class
UserJwtController {

    private final UserJwtService userJwtService;

    /**
     * 로그인 - jwt
     *
     * @param loginReqDto userEmail & userPassword
     * @return userSeq
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletRequest request, HttpServletResponse response) {

        UserLoginRespDto userLoginRespDto = userJwtService.login(loginReqDto);

        if (userLoginRespDto != null) {

            // 기존 쿠키 삭제
            CookieUtil.deleteCookie(request, response, "refreshToken");
            // JWT 쿠키 저장(쿠키 명 : token)
            CookieUtil.addCookie(response, "refreshToken", userLoginRespDto.getRefreshToken());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, userLoginRespDto.getAccessToken());

            return ResponseEntity.status(200).headers(headers).body(ResponseBody.create(200, "success", userLoginRespDto));
        }
        // 로그인 정보가 비어있는 경우
        else {
            return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
        }
    }

    @GetMapping("/logout/{userSeq}")
    public ResponseEntity<?> logout(@PathVariable Long userSeq, HttpServletRequest request) {

        String accessToken = request.getHeader("Authorization");

        if (userJwtService.logout(userSeq)) {
            HttpSession session = request.getSession();
            session.setAttribute(accessToken, accessToken);
            session.setMaxInactiveInterval(30 * 60);
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(403).body(ResponseBody.create(403, "fail"));
    }

}
