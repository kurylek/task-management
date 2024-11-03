package com.kurylek.task.management.application.rest.user;

public record UserDto (Long id,
                       String firstName,
                       String lastName,
                       String email) {
}
