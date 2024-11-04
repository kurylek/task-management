--liquibase formatted sql

--changeset kurylek:1.create users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
--rollback drop table users;

--changeset kurylek:2.create tasks table
CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(15) CHECK (status IN ('TODO', 'IN_PROGRESS', 'DONE', 'CANCELED')) NOT NULL,
    due_date TIMESTAMP  NOT NULL
    );
--rollback drop table tasks;

--changeset kurylek:3.create task_assigned_users table
CREATE TABLE IF NOT EXISTS task_assigned_users  (
    task_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    PRIMARY KEY (task_id, user_id),
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
    );
--rollback drop table task_assigned_users ;
