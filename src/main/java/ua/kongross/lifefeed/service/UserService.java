package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

public interface UserService extends UserDetailsService {
    User signUpUser(SignUpRequest request);

    User findUserById(Long id);

    User getUserData(Long id);

    void subscribe(Long id, UserDetails userDetails);
}
