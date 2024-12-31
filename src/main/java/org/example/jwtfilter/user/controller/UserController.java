package org.example.jwtfilter.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @GetMapping("/get")
    public String getUserInfo(HttpServletRequest request) {
        log.info("유저 페이지 호출");
        return "유저 페이지 리소스가 허가 되었습니다.";
    }

}
