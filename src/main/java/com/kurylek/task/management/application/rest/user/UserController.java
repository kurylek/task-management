package com.kurylek.task.management.application.rest.user;

import com.kurylek.task.management.domain.user.UserApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApiPort userApiPort;

}
