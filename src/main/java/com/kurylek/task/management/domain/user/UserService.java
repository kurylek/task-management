package com.kurylek.task.management.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {

    private final UserStoragePort userStoragePort;

    @Override
    public List<User> getFilteredUsers(String firstName, String lastName, String email) {
        return userStoragePort.getFilteredUsers(firstName, lastName, email);
    }

    @Override
    public User createUser(User user) {
        return userStoragePort.createUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userStoragePort.deleteUserById(userId);
    }
}
