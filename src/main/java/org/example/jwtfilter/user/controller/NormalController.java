package org.example.jwtfilter.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jwtfilter.user.model.request.CreateUserRequest;
import org.example.jwtfilter.user.model.response.CreateUserResponse;
import org.example.jwtfilter.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/normal")
@RequiredArgsConstructor
@Slf4j
public class NormalController {

    private final UserService userService;

    @GetMapping("/get")
    public String getUserInfo(HttpServletRequest request) {
        log.info("일반적으로 모두 사용할 수 있는 페이지 호출");
        return "일반 페이지 리소스가 허가 되었습니다.";
    }

    @PostMapping("/v1/create")
    public CreateUserResponse createUserV1(@RequestBody CreateUserRequest request) {
        return userService.createUserV1(request);
    }

    @PostMapping("/v2/create")
    public CreateUserResponse createUserV2(@RequestBody CreateUserRequest request) throws IOException {
        return userService.createUserV2(request);
    }

    @PostMapping("/v3/create")
    public CreateUserResponse createUserV3(@RequestBody CreateUserRequest request) throws IOException {
        return userService.createUserV3(request);
    }

    @PostMapping("/v4/create")
    public CreateUserResponse createUserV4(@RequestBody CreateUserRequest request) throws SQLException {
        return userService.createUserV4(request);
    }

    @PostMapping("/v5/create")
    public CreateUserResponse createUserV5(@RequestBody CreateUserRequest request) throws SQLException {
        return userService.createUserV5(request);
    }
}
