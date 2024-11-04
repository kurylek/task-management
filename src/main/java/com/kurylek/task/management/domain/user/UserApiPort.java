package com.kurylek.task.management.domain.user;

import java.util.List;

public interface UserApiPort {

    User getUserById(Long userId);

    List<User> getFilteredUsers(String firstName, String lastName, String email);

    User createUser(User user);

    void deleteUser(Long userId);
}
