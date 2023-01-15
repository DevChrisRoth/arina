package de.revaxlabs.backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.awt.*;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getUser(){
        return "user";
    }

    @GetMapping(path = "/pageable", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<UserDto> readPagable(@NotNull final Pageable pageable){
        return userService.getAllUsers(pageable);
    }
}
