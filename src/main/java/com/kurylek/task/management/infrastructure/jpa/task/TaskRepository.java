package com.kurylek.task.management.infrastructure.jpa.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>,
        JpaSpecificationExecutor<TaskEntity> {
}
