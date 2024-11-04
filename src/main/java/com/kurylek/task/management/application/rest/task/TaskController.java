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

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> editTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskDto taskDto) {
        Task updatedTask = taskApiPort.updateTask(taskId, taskMapper.mapToModel(taskDto));
        return ResponseEntity.ok(taskMapper.mapToDto(updatedTask));
    }

    @PatchMapping("/change-status/{taskId}")
    public ResponseEntity<TaskDto> changeTaskStatus(
            @PathVariable Long taskId,
            @RequestParam TaskStatus status) {
        Task updatedTask = taskApiPort.updateTaskStatus(taskId, status);
        return ResponseEntity.ok(taskMapper.mapToDto(updatedTask));
    }

    @PostMapping("/add-user/{taskId}/{userId}")
    public ResponseEntity<TaskDto> addUserToTask(
            @PathVariable Long taskId,
            @PathVariable Long userId) {
        Task updatedTask = taskApiPort.addUserToTask(taskId, userId);
        return ResponseEntity.ok(taskMapper.mapToDto(updatedTask));
    }
}
