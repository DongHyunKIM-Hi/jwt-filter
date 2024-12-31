package org.example.jwtfilter.user.service;

import java.io.IOException;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.example.jwtfilter.common.entity.User;
import org.example.jwtfilter.common.utils.JwtUtil;
import org.example.jwtfilter.common.utils.PasswordEncoder;
import org.example.jwtfilter.user.model.request.CreateUserRequest;
import org.example.jwtfilter.user.model.request.LoginRequest;
import org.example.jwtfilter.user.model.response.CreateUserResponse;
import org.example.jwtfilter.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String login(LoginRequest request) {

        String userName = request.userName();
        String password = request.password();

        User user = userRepository.findByUsername(userName).orElseThrow(
            () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    // 롤백 체험하기
    @Transactional
    public CreateUserResponse createUserV1(CreateUserRequest request) {
        User user = User.createUser(request);
        userRepository.save(user);
        throw new RuntimeException();
    }

    @Transactional
    public CreateUserResponse createUserV2(CreateUserRequest request) throws IOException {
        User user = User.createUser(request);
        userRepository.save(user);
        throw new IOException();
    }

    @Transactional(rollbackFor = IOException.class)
    public CreateUserResponse createUserV3(CreateUserRequest request) throws IOException {
        User user = User.createUser(request);
        userRepository.save(user);
        throw new IOException();
    }

    @Transactional(rollbackFor = Exception.class)
    public CreateUserResponse createUserV4(CreateUserRequest request) throws SQLException {
        User user = User.createUser(request);
        userRepository.save(user);
        throw new SQLException();
    }

    @Transactional(noRollbackFor = RuntimeException.class)
    public CreateUserResponse createUserV5(CreateUserRequest request) {
        User user = User.createUser(request);
        userRepository.save(user);
        throw new RuntimeException();
    }


    // InitDat 저장용 으로 만든 메서드임
    public User save(User user) {
        return userRepository.save(user);
    }
}
