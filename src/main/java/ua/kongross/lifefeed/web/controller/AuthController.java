package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign_up")
    public void signUp(final SignUpRequest request) {
        userService.signUpUser(request);
    }
}
