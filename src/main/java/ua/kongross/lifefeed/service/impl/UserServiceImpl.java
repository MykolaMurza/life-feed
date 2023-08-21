package ua.kongross.lifefeed.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Role;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.UserRepository;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.ProfileDto;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final String NO_USER_WITH_SUCH_ID_MESSAGE = "There is no user with such ID!";
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
    public void signUpUser(SignUpRequest request) {
        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(Role.ROLE_USER);
        user.setSubscribers(new ArrayList<>());

        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException(NO_USER_WITH_SUCH_ID_MESSAGE);
    }

    @Override
    public ProfileDto getUserData(Long id, User user) {
        User userToWatchProfileAndFeed = findUserById(id);

        return ProfileDto.builder()
                .id(userToWatchProfileAndFeed.getId())
                .username(userToWatchProfileAndFeed.getUsername())
                .email(userToWatchProfileAndFeed.getEmail())
                .name(userToWatchProfileAndFeed.getName())
                .surname(userToWatchProfileAndFeed.getSurname())
                .subscribers(userToWatchProfileAndFeed.getSubscribers())
                .subscribed(user != null && user.getSubscribers().contains(userToWatchProfileAndFeed))
                .build(); //todo add privacy
    }

    @Override
    @Transactional
    public void subscribe(Long id, UserDetails userDetails) {
        Optional<User> userToSub = userRepository.findById(id);
        if (userToSub.isPresent()) {
            User user = (User) userDetails;
            Collection<User> subscribers = user.getSubscribers();
            if (!subscribers.contains(userToSub.get())) {
                subscribers.add(userToSub.get());
                userRepository.save(user);
            }
        } else throw new UsernameNotFoundException(NO_USER_WITH_SUCH_ID_MESSAGE);
    }

    @Override
    public void unsubscribe(Long id, UserDetails userDetails) {
        Optional<User> userToUnsub = userRepository.findById(id);
        if (userToUnsub.isPresent()) {
            User user = (User) userDetails;
            Collection<User> subscribers = user.getSubscribers();
            if (subscribers.contains(userToUnsub.get())) {
                subscribers.remove(userToUnsub.get());
                userRepository.save(user);
            }
        } else throw new UsernameNotFoundException(NO_USER_WITH_SUCH_ID_MESSAGE);
    }

    @Override
    public void block(Long id, Boolean block, UserDetails userDetails) {

    }
}
