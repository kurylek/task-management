package com.kurylek.task.management.application.rest.task;

import com.kurylek.task.management.domain.task.Task;
import com.kurylek.task.management.domain.task.TaskApiPort;
import com.kurylek.task.management.domain.task.TaskStatus;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskApiPort taskApiPort;
    private final TaskDtoMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getFilteredTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) TaskStatus status) {
        return ResponseEntity.ok(taskMapper.mapToDto(taskApiPort.getFilteredTasks(title, description, status)));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        Task createdTask = taskApiPort.createTask(taskMapper.mapToModel(taskDto));
        return ResponseEntity.ok(taskMapper.mapToDto(createdTask));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskApiPort.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}
