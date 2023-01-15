package de.revaxlabs.backend.UserLogin;

import de.revaxlabs.backend.util.CustomPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLoginService {

    private final UserLoginPagingRepository userLoginPagingRepository;
    private final UserLoginJpaRepository userLoginJpaRepository;
    private final ModelMapper modelMapper;

    public String authenticateUser(UserLoginDto userLoginDto){
        UserLogin userLogin = userLoginJpaRepository.findByEmail(userLoginDto.getEmail());
        String storedPasswordHash = userLogin.getPassword();
        boolean isPasswordValid = BCrypt.checkpw(userLoginDto.getPassword(), storedPasswordHash);
        if(isPasswordValid){
            return "true";
        } else {
            throw new IllegalArgumentException("Password or Email not correct");
        }
    }

    public CustomPage<UserLoginDto> getCustomPaged(Pageable pageable){
        Page<UserLogin> userPage = userLoginPagingRepository.findAll(pageable);
        return new CustomPage<>(userPage.map(user -> modelMapper.map(user, UserLoginDto.class)));
    }
}
