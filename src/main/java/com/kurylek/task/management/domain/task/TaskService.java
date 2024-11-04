package com.kurylek.task.management.domain.task;

import com.kurylek.task.management.domain.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskApiPort {

    private final TaskStoragePort taskStoragePort;

    @Override
    public List<Task> getFilteredTasks(String title, String description, TaskStatus status) {
        return taskStoragePort.getFilteredTasks(title, description, status);
    }

    @Override
    public Task createTask(Task task) {
        task.setStatus(TaskStatus.TODO);
        task.setAssignedUsers(new ArrayList<>());

        return taskStoragePort.createTask(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Optional<Task> taskToDelete = taskStoragePort.getTaskById(taskId);

        if (taskToDelete.isPresent()) {
            taskStoragePort.deleteTaskById(taskId);
        } else {
            throw new NotFoundException(String.format("Task with id %s not found", taskId));
        }
    }
}
