package de.arina.backend.UserLogin;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserLoginDto {


    @NotNull
    private String email;

    @NotNull
    private String password;

}
