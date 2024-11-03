package com.kurylek.task.management.application.rest.user;

import com.kurylek.task.management.domain.user.User;
import com.kurylek.task.management.domain.user.UserApiPort;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApiPort userApiPort;
    private final UserDtoMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserDto>> getFilteredUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(userMapper.mapToDto(userApiPort.getFilteredUsers(firstName, lastName, email)));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        User createdUser = userApiPort.createUser(userMapper.mapToModel(userDto));
        return ResponseEntity.ok(userMapper.mapToDto(createdUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userApiPort.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
