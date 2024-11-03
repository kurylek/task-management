package com.kurylek.task.management.infrastructure.jpa.user;

import com.kurylek.task.management.domain.user.UserStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStorage implements UserStoragePort {

    private final UserRepository userRepository;
}
