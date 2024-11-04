package com.kurylek.task.management.domain.task;

import com.kurylek.task.management.domain.exception.NotFoundException;
import com.kurylek.task.management.domain.exception.UserAlreadyAssignedException;
import com.kurylek.task.management.domain.user.User;
import com.kurylek.task.management.domain.user.UserApiPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskStoragePort taskStoragePort;

    @Mock
    private UserApiPort userApiPort;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldThrowExceptionWhenUserIsAlreadyAssignedToTask() {
        //GIVEN: Task exists in database
        Long taskId = 1L;
        Long userId = 1L;

        when(taskStoragePort.getTaskById(taskId))
                .thenReturn(Optional.of(new Task(1L, "title", "dsc", TaskStatus.TODO,
                        LocalDateTime.now(), List.of(new User(userId, "John", "Doe", "jdoe@gm.ail")))));

        //WHEN: Adding user to task
        //THEN: Exception is threw
        assertThrows(UserAlreadyAssignedException.class, () -> taskService.addUserToTask(taskId, userId));
    }
}