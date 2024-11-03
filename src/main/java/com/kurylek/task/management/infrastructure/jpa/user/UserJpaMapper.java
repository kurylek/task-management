package com.kurylek.task.management.infrastructure.jpa.user;

import com.kurylek.task.management.domain.user.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserJpaMapper {
    UserEntity mapToEntity(User user);

    User mapToModel(UserEntity userEntity);

    List<User> mapToModel(List<UserEntity> userEntities);
}
