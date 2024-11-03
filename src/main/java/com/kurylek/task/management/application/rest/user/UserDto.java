package com.kurylek.task.management.application.rest.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto (@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
                       @NotBlank String firstName,
                       @NotBlank String lastName,
                       @NotBlank @Email String email) {
}
