package de.revaxlabs.backend.UserLogin;

import de.revaxlabs.backend.util.CustomPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLoginController {

    private final UserLoginService userLoginService;

    @PostMapping(path = "/auth", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String authUserController(@RequestBody UserLoginDto userLoginDto){
        return userLoginService.authenticateUser(userLoginDto);
    }
    @GetMapping(path = "/c", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomPage<UserLoginDto> getCustomPageEntries(@NotNull final Pageable pageable){
        return userLoginService.getCustomPaged(pageable);
    }
}
