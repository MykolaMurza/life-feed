package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.ProfileDto;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

public interface UserService extends UserDetailsService {
    void signUpUser(SignUpRequest request);

    User findUserById(Long id);

    ProfileDto getUserData(Long id, User user);

    void subscribe(Long id, UserDetails userDetails);

    void unsubscribe(Long id, UserDetails userDetails);

    void block(Long id, Boolean block, UserDetails userDetails);
}
