package com.kurylek.task.management.domain.user;

import java.util.List;

public interface UserStoragePort {

    List<User> getFilteredUsers(String firstName, String lastName, String email);

    User createUser(User user);

    void deleteUserById(Long userId);
}
