package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

/**
 * Provide CRUD API for user's own posts.
 *
 * @author murza
 */
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public String createPost(final CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        if (!request.getText().isBlank())
            postService.createPost(request, (User) userDetails);

        return "";
    }

    @PatchMapping
    public String updatePost(final CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        if (!request.getText().isBlank())
            postService.updatePost(request, (User) userDetails);

        return "";
    }

    @GetMapping("/{id}")
    public String deletePost(@PathVariable final Long id, @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, (User) userDetails);

        return "";
    }
}
