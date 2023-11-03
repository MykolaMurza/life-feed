package ua.kongross.lifefeed.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Role;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.AuthService;
import ua.kongross.lifefeed.service.UserService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String SECRET_KEY_VALUE = "rjv2tA4UXb2ZNd8iv8lCU97g8Exfs5KMrjv2tA4UXb2ZNd8iv8lCU97g8Exfs5KM"; // TODO: move and change
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET_KEY_VALUE.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION_TIME = 864_000_000; // TODO: move, 10 days

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void signUp(String username, String password, String email) {
        User user = new User();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(Role.ROLE_USER);
        user.setSubscribers(new ArrayList<>());

        userService.save(user);
    }

    @Override
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return generateToken(authentication);
    }

    private String generateToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }
}
