package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getMyProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        model.addAttribute("feed", postService.getProfilePosts(user.getId(), user).getPosts());
        model.addAttribute("userData", userService.getUserData(user.getId(), null));

        return "profile_me";
    }

    @GetMapping("/{id}")
    public String getProfile(Model model, @PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;
        if (Objects.equals(id, user.getId())) return "redirect:/feed/profile/me";

        model.addAttribute("feed", postService.getProfilePosts(id, user).getPosts());
        model.addAttribute("userData", userService.getUserData(id, user));

        return "profile";
    }
}
