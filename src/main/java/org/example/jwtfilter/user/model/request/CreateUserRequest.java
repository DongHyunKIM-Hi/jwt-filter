package org.example.jwtfilter.user.model.request;

import org.example.jwtfilter.common.enums.UserRoleEnum;

public record CreateUserRequest(
    String userName,
    String password,
    String email,
    UserRoleEnum role
) {

}
