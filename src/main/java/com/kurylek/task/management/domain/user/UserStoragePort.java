package com.kurylek.task.management.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserStoragePort {

    Optional<User> getUserById(Long userId);

    List<User> getFilteredUsers(String firstName, String lastName, String email);

    User createUser(User user);

    void deleteUserById(Long userId);
}
