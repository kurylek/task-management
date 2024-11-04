package com.kurylek.task.management.infrastructure.jpa.task;

import com.kurylek.task.management.domain.task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskJpaMapper {
    TaskEntity mapToEntity(Task task);

    Task mapToModel(TaskEntity taskEntity);

    List<Task> mapToModel(List<TaskEntity> taskEntities);
}
