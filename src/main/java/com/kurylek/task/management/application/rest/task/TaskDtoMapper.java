package com.kurylek.task.management.application.rest.task;

import com.kurylek.task.management.domain.task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskDtoMapper {
    List<TaskDto> mapToDto(List<Task> tasks);

    TaskDto mapToDto(Task task);

    Task mapToModel(TaskDto taskDto);
}
