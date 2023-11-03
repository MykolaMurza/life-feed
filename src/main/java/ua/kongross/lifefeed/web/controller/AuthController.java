package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.kongross.lifefeed.service.AuthService;
import ua.kongross.lifefeed.web.dto.request.LoginRequest;
import ua.kongross.lifefeed.web.dto.request.SignUpRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestBody final SignUpRequest request) {
        authService.signUp(request.getUsername(), request.getPassword(), request.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginRequest request) {
        String jwt = authService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(jwt);
    }
}
