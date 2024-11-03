package com.kurylek.task.management.infrastructure.jpa.user;

import com.kurylek.task.management.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class UserStorageIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Spy
    private UserJpaMapper userMapper = new UserJpaMapperImpl();

    private UserStorage userStorage;

    @BeforeEach
    void setUp() {
        userStorage = new UserStorage(userRepository, userMapper);
    }

    @Test
    void getFilteredUsers() {
        //Given: In database exist two users
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";

        userRepository.save(new UserEntity(1L, firstName, lastName, email));
        userRepository.save(new UserEntity(2L, "Jane", "Smith", "jane.smith@example.com"));


        //When: Filtering users by first name
        List<User> result = userStorage.getFilteredUsers(firstName, null, null);

        //Then: Fetched first user
        assertEquals(1, result.size());
        assertEquals(firstName, result.get(0).getFirstName());
        assertEquals(lastName, result.get(0).getLastName());
        assertEquals(email, result.get(0).getEmail());
    }
}
