package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.FeedService;
import ua.kongross.lifefeed.service.UserService;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;
    private final UserService userService;

    @GetMapping
    public String getPosts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        feedService.getPosts(user).getPosts();

        return "";
    }

    @PostMapping("/subscribe/{id}")
    public String subscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.subscribe(id, userDetails);

        return "";
    }

    @PostMapping("/unsubscribe/{id}")
    public String unsubscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.unsubscribe(id, userDetails);

        return "";
    }
}
