package ua.kongross.lifefeed.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.kongross.lifefeed.database.entity.Role;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.UserRepository;
import ua.kongross.lifefeed.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        if (Lists.newArrayList(userRepository.findAll()).isEmpty()) {
            User user = new User();
            user.setEmail("email@email.com");
            user.setUsername("username");
            user.setPassword(new BCryptPasswordEncoder().encode("password"));
            user.setRole(Role.ROLE_USER);
            userRepository.save(user);
            log.info("Default user created!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no user with such username!");
    }
}
