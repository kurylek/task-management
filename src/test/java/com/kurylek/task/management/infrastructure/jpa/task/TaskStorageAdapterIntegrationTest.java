package com.kurylek.task.management.infrastructure.jpa.task;

import com.kurylek.task.management.domain.task.Task;
import com.kurylek.task.management.domain.task.TaskStatus;
import com.kurylek.task.management.domain.user.User;
import com.kurylek.task.management.infrastructure.jpa.user.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskStorageAdapterIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Spy
    private TaskJpaMapper taskMapper = new TaskJpaMapperImpl();

    private TaskStorageAdapter taskStorage;

    @BeforeEach
    void setUp() {
        taskStorage = new TaskStorageAdapter(taskRepository, taskMapper);
    }

    @Test
    void getFilteredTasks() {
        //Given: Two tasks exist in database
        taskRepository.save(new TaskEntity(1L, "Task1", "Description", TaskStatus.TODO,
                LocalDateTime.now(), List.of()));
        taskRepository.save(new TaskEntity(2L, "Task2", "Description", TaskStatus.DONE,
                LocalDateTime.now(), List.of()));


        //When: Filtering tasks by status
        List<Task> result = taskStorage.getFilteredTasks(null, null, TaskStatus.DONE);

        //Then: Fetched status with done status
        assertEquals(1, result.size());
        assertEquals(TaskStatus.DONE, result.get(0).getStatus());
    }
}