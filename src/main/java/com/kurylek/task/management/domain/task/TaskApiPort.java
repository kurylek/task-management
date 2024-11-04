package com.kurylek.task.management.domain.task;

import java.util.List;

public interface TaskApiPort {

    List<Task> getFilteredTasks(String title, String description, TaskStatus status);

    Task createTask(Task task);

    void deleteTask(Long taskId);
}
