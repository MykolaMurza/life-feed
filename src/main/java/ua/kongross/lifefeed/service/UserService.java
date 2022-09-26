package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

public interface UserService extends UserDetailsService {
    User signUpUser(SignUpRequest request);
}
