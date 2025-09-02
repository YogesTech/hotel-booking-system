package com.yogesh.hotel_booking_system.repositories;

import com.yogesh.hotel_booking_system.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");

        userRepository.save(user);

        User found = userRepository.findByUsername("testuser").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("testuser");
    }
}
