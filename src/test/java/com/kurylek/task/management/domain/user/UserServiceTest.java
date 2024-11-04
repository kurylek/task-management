package com.kurylek.task.management.domain.user;

import com.kurylek.task.management.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserStoragePort userStoragePort;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldDeleteUserIfExists() {
        //GIVEN: User exists in database
        Long userId = 1L;

        when(userStoragePort.getUserById(userId))
                .thenReturn(Optional.of(new User(userId, "John", "Doe", "jdoe@gmail.com")));

        //WHEN: Deleting user
        userService.deleteUser(userId);

        //THEN: User is deleted
        verify(userStoragePort, times(1)).deleteUserById(userId);
    }

    @Test
    void shouldThrowNotFoundExceptionWhileDeletingNonExistentUser() {
        //GIVEN: User doesn't exist in database
        Long userId = 1L;

        when(userStoragePort.getUserById(userId))
                .thenReturn(Optional.empty());

        //WHEN: Deleting user
        //THEN: Exception is threw
        assertThrows(NotFoundException.class, () -> userService.deleteUser(userId));
        verify(userStoragePort, never()).deleteUserById(userId);
    }
}