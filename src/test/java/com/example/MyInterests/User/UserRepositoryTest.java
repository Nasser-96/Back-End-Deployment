package com.example.MyInterests.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void itShouldFindUserByEmail() {
        String email = "n@gmail.com";
        User user = new User(email,"123");
        userRepository.save(user);

        User result = userRepository.findByEmail(email);
        assertEquals(email,result.getEmail());
    }

    @Test
    void itShouldSaveUser(){
        User user = new User("n@gmail.com","123");
        User result = userRepository.save(user);

        assertNotNull(result.getId());
    }

    // Update, Delete
}