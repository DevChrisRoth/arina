package de.revaxlabs.backend.user;

import de.revaxlabs.backend.util.HalResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final HalResponseService halResponseService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<UserDto> getAllUsers(Pageable pageable){
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::toUserDto);
    }
}
