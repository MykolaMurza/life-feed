package ua.kongross.lifefeed.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Role;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.UserRepository;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException("There is no user with such username!");
    }

    @Override
    public User signUpUser(SignUpRequest request) {
        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }
}
