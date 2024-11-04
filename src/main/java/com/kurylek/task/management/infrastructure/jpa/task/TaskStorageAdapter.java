package com.kurylek.task.management.infrastructure.jpa.task;

import com.kurylek.task.management.domain.task.Task;
import com.kurylek.task.management.domain.task.TaskStatus;
import com.kurylek.task.management.domain.task.TaskStoragePort;
import com.kurylek.task.management.infrastructure.jpa.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskStorageAdapter implements TaskStoragePort {

    private final TaskRepository taskRepository;
    private final TaskJpaMapper taskMapper;

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .map(taskMapper::mapToModel);
    }

    @Override
    public List<Task> getFilteredTasks(String title, String description, TaskStatus status) {
        Specification<TaskEntity> spec = createFilteringSpecification(title, description, status);
        return taskMapper.mapToModel(taskRepository.findAll(spec));
    }

    @Override
    public Task saveTask(Task task) {
        return taskMapper.mapToModel(
                taskRepository.save(taskMapper.mapToEntity(task))
        );
    }

    @Override
    public void deleteTaskById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private Specification<TaskEntity> createFilteringSpecification(String title, String description, TaskStatus status) {
        Specification<TaskEntity> spec = Specification.where(null);

        if (!StringUtils.isBlank(title)) spec = spec.and(getSpecificationLike("title", title));
        if (!StringUtils.isBlank(description)) spec = spec.and(getSpecificationLike("description", description));
        if (status != null) spec = spec.and(getStatusSpecification(status));

        return spec;
    }

    private Specification<TaskEntity> getSpecificationLike(String fieldName, String likeValue) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(fieldName), String.format("%%%s%%", likeValue));
    }

    private Specification<TaskEntity> getStatusSpecification(TaskStatus status) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), status);
    }
}
