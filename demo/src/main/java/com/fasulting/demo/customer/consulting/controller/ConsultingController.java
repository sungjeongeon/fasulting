package com.fasulting.demo.customer.consulting.controller;

import com.fasulting.demo.customer.consulting.service.ConsultingService;
import com.fasulting.demo.customer.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 논의 부분

@Slf4j
@RestController
@RequestMapping("/consulting")
@CrossOrigin("*") // 수정
public class ConsultingController {

    private ConsultingService consultingService;

    // 1. 상담 입장
    @GetMapping("/consulting/{consultingId}/{userId}")
    public ResponseEntity<?> EnterMeetingRoom(@PathVariable("consultingId") int consultingId, @PathVariable("userId") int userId) {
        return null; // success OR fail
        // success: 상담화면으로 리다이렉트
    }

    // 2. 상담 사전 진행


    // 3. 상담 진행


    // 4. 상담 종료


}
