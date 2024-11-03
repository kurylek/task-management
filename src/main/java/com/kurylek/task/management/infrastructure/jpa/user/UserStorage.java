package com.kurylek.task.management.infrastructure.jpa.user;

import com.kurylek.task.management.domain.user.User;
import com.kurylek.task.management.domain.user.UserStoragePort;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStorage implements UserStoragePort {

    private final UserRepository userRepository;
    private final UserJpaMapper userMapper;

    @Override
    public List<User> getFilteredUsers(String firstName, String lastName, String email) {
        Specification<UserEntity> spec = createFilteringSpecification(firstName, lastName, email);
        return userMapper.mapToModel(userRepository.findAll(spec));
    }

    @Override
    public User createUser(User user) {
        return userMapper.mapToModel(
                userRepository.save(userMapper.mapToEntity(user))
        );
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    private Specification<UserEntity> createFilteringSpecification(String firstName, String lastName, String email) {
        Specification<UserEntity> spec = Specification.where(null);

        if (!StringUtils.isBlank(firstName)) spec = spec.and(getSpecificationLike("firstName", firstName));
        if (!StringUtils.isBlank(lastName)) spec = spec.and(getSpecificationLike("lastName", lastName));
        if (!StringUtils.isBlank(email)) spec = spec.and(getSpecificationLike("email", email));

        return spec;
    }

    private Specification<UserEntity> getSpecificationLike(String fieldName, String likeValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(fieldName), String.format("%%%s%%", likeValue));
    }
}
