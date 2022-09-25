package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.FeedDto;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
    private final PostService postService;

    @GetMapping
    public FeedDto getPosts() {
        return postService.getPosts();
    }
}
