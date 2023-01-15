package de.revaxlabs.backend.user;

import org.mapstruct.Mapper;
@Mapper
public interface UserMapper {
    UserDto toUserDto(final User user);
}
