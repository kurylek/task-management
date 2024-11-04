package com.kurylek.task.management.application.rest.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kurylek.task.management.application.rest.user.UserDto;
import com.kurylek.task.management.domain.task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record TaskDto (@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
                       @NotBlank String title,
                       @NotBlank String description,
                       @JsonProperty(access = JsonProperty.Access.READ_ONLY) TaskStatus status,
                       @NotNull LocalDateTime dueDate,
                       @JsonProperty(access = JsonProperty.Access.READ_ONLY) List<UserDto> assignedUsers) {
}
