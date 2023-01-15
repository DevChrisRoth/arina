package de.revaxlabs.backend.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    @NotNull
    private String email = "email";

    @NotNull
    private String passwordHash = "pw";

}
