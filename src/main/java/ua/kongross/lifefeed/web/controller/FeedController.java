package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.FeedService;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.FeedPostDto;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;
    private final UserService userService;

    @GetMapping
    public List<FeedPostDto> getPosts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return feedService.getPosts(user).getPosts();
    }

    @PostMapping("/subscribe/{id}")
    public void subscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.subscribe(id, userDetails);
    }

    @PostMapping("/unsubscribe/{id}")
    public void unsubscribe(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        userService.unsubscribe(id, userDetails);
    }
}
