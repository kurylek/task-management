package com.kurylek.task.management.domain.task;

import java.util.List;
import java.util.Optional;

public interface TaskStoragePort {

    Optional<Task> getTaskById(Long taskId);

    List<Task> getFilteredTasks(String title, String description, TaskStatus status);

    Task saveTask(Task task);

    void deleteTaskById(Long taskId);
}
