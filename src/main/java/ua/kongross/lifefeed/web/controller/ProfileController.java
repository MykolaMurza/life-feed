package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/me")
    public String getMyProfile(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return "";
    }

    @GetMapping("/{id}")
    public String getProfile(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        if (Objects.equals(id, user.getId())) return "redirect:/feed/profile/me";

        return "";
    }
}
