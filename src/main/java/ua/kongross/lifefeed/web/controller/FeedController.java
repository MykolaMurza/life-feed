package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.FeedService;
import ua.kongross.lifefeed.web.dto.PostDto;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @GetMapping
    public List<PostDto> getMyPosts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return feedService.getFeed(user).getPosts();
    }

    @GetMapping("/{id}")
    public List<PostDto> getUserFeed(@PathVariable final Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return feedService.getFeed(id, user).getPosts();
    }

    @GetMapping("/subbed")
    public List<PostDto> getSubbedFeed(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return feedService.getSubbedFeed(user).getPosts();
    }

    @GetMapping("/global")
    public List<PostDto> getGlobalPosts(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;

        return feedService.getGlobalFeed(user).getPosts();
    }
}
