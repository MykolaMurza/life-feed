package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign_up")
    public String signUp(final SignUpRequest request) {
        userService.signUpUser(request);

        return "";
    }
}
