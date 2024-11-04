package com.kurylek.task.management.domain.task;

import com.kurylek.task.management.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime dueDate;

    private List<User> assignedUsers;
}
