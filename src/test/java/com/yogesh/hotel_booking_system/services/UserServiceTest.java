package com.yogesh.hotel_booking_system.services;

import com.yogesh.hotel_booking_system.models.User;
import com.yogesh.hotel_booking_system.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserService(userRepository);

    @Test
    void testFindUserByUsername() {
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("mockuser");
        mockUser.setPassword("pass");
        mockUser.setRole("USER");
        when(userRepository.findByUsername("mockuser")).thenReturn(Optional.of(mockUser));

        User found = userService.findByUsername("mockuser").orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("mockuser");
        verify(userRepository, times(1)).findByUsername("mockuser");
    }
}
