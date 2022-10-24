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
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public String createPost(final CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        postService.createPost(request, (User) userDetails);

        return "redirect:/feed";
    }

    @GetMapping("/{id}")
    public String deletePost(@PathVariable final Long id, @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, (User) userDetails);

        return "redirect:/feed";
    }
}
