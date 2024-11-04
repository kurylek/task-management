package com.kurylek.task.management.domain.task;

import com.kurylek.task.management.domain.exception.NotFoundException;
import com.kurylek.task.management.domain.exception.UserAlreadyAssignedException;
import com.kurylek.task.management.domain.user.User;
import com.kurylek.task.management.domain.user.UserApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskApiPort {

    private final TaskStoragePort taskStoragePort;
    private final UserApiPort userApiPort;

    @Override
    public List<Task> getFilteredTasks(String title, String description, TaskStatus status) {
        return taskStoragePort.getFilteredTasks(title, description, status);
    }

    @Override
    public Task createTask(Task task) {
        task.setStatus(TaskStatus.TODO);
        task.setAssignedUsers(new ArrayList<>());

        return taskStoragePort.saveTask(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task taskToDelete = getTaskIfExist(taskId);
        taskStoragePort.deleteTaskById(taskToDelete.getId());
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task taskToUpdate = getTaskIfExist(taskId);

        taskToUpdate.setTitle(task.getTitle());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDueDate(task.getDueDate());

            return taskStoragePort.saveTask(taskToUpdate);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task taskToUpdate = getTaskIfExist(taskId);

        taskToUpdate.setStatus(status);

        return taskStoragePort.saveTask(taskToUpdate);
    }

    @Override
    public Task addUserToTask(Long taskId, Long userId) {
        Task taskToUpdate = getTaskIfExist(taskId);

        if (isUserAssigned(taskToUpdate.getAssignedUsers(), userId)) {
            throw new UserAlreadyAssignedException(String.format("User with id %s is already assigned to task with id %s",
                    userId, taskId));
        }

        User userToAdd = userApiPort.getUserById(userId);

        taskToUpdate.getAssignedUsers().add(userToAdd);

        return taskStoragePort.saveTask(taskToUpdate);
    }

    private boolean isUserAssigned(List<User> assignedUsers, Long userId) {
        return assignedUsers.stream()
                .anyMatch(user -> user.getId().equals(userId));
    }

    private Task getTaskIfExist(Long taskId) {
        Optional<Task> task = taskStoragePort.getTaskById(taskId);

        if (task.isPresent()) {
            return task.get();
        } else {
            throw new NotFoundException(String.format("Task with id %s not found", taskId));
        }
    }
}
