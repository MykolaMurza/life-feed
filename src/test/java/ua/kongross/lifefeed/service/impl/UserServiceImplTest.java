package ua.kongross.lifefeed.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.kongross.lifefeed.database.entity.Role;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.UserRepository;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsernameSuccessTest() {
        User user = new User();
        user.setUsername("username");

        Mockito.when(userRepository.findUserByUsername(Mockito.anyString()))
                .thenReturn(Optional.of(user));

        UserDetails result = userService.loadUserByUsername("username");

        Assertions.assertEquals(user, result);
    }

    @Test
    void loadUserByUsernameNotFoundTest() {
        Mockito.when(userRepository.findUserByUsername(Mockito.anyString()))
                .thenReturn(Optional.empty());

        userService.loadUserByUsername("username");

        Throwable exception = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("username"));
        Assertions.assertEquals("There is no user with such username!", exception.getMessage());
    }

    @Test
    void signUpUserTest() {
        SignUpRequest request = new SignUpRequest();
        request.setUsername("username");
        request.setEmail("email@example.com");
        request.setPassword("password");

        userService.signUpUser(request);

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    void findUserById() {
    }

    void getUserData() {
    }

    @Test
    void whenSubscribeUserWithExistingId_thenSubscribeShouldBeSuccessful() {
        User userToSubscribe = new User();
        userToSubscribe.setId(1L);
        userToSubscribe.setUsername("test_user");
        userToSubscribe.setEmail("test_user@gmail.com");
        userToSubscribe.setPassword("test_password");
        userToSubscribe.setName("Test");
        userToSubscribe.setSurname("User");
        userToSubscribe.setRole(Role.ROLE_USER);
        userToSubscribe.setSubscribers(new ArrayList<>());
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userToSubscribe));

        UserDetails userDetails = new User();

        userService.subscribe(1L, userDetails);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    void whenSubscribeUserWithNonExistingId_thenExceptionShouldBeThrown() {
        Mockito.when(userRepository.findById(100L)).thenReturn(Optional.empty());

        UserDetails userDetails = new User();

        Throwable exception = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userService.subscribe(100L, userDetails));
        Assertions.assertEquals("There is no user with such ID!", exception.getMessage());
        Mockito.verify(userRepository, Mockito.times(1)).findById(100L);
    }

    void unsubscribe() {
    }
}