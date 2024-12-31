package org.example.jwtfilter.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.jwtfilter.common.enums.UserRoleEnum;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {

    private String userName;
    private String password;
    private String email;
    private UserRoleEnum role;
}
