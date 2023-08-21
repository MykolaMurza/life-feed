package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.ProfileDto;

import java.util.Objects;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return new ProfileDto();
    }

    @GetMapping("/{id}")
    public ProfileDto getProfile(@PathVariable Long id,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        if (Objects.equals(id, user.getId())) return getMyProfile(userDetails);

        return new ProfileDto();
    }
}
