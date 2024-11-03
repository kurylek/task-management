package com.kurylek.task.management.application.rest.user;

import com.kurylek.task.management.domain.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserDtoMapper {
    List<UserDto> mapToDto(List<User> users);

    UserDto mapToDto(User user);

    User mapToModel(UserDto userDto);
}
