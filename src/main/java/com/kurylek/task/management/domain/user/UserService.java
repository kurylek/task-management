package com.kurylek.task.management.domain.user;

import com.kurylek.task.management.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {

    private final UserStoragePort userStoragePort;

    @Override
    public User getUserById(Long userId) {
        Optional<User> user = userStoragePort.getUserById(userId);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException(String.format("User with id %s not found", userId));
        }
    }

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
        User userToDelete = getUserById(userId);
        userStoragePort.deleteUserById(userToDelete.getId());
    }
}
