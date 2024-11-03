package com.kurylek.task.management.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {

    private final UserStoragePort userStoragePort;
}
