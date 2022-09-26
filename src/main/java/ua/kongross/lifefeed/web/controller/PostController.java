package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public String createPost(final CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        postService.createPost(request, userDetails);

        return "redirect:/feed";
    }
}
