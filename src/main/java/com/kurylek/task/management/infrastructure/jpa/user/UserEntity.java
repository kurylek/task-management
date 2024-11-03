package com.kurylek.task.management.infrastructure.jpa.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
