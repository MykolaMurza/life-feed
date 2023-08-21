package ua.kongross.lifefeed.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.request.ComplainPostRequest;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.request.UpdatePostRequest;
import ua.kongross.lifefeed.web.dto.request.VotePostRequest;

/**
 * Provide CRUD API for user's own posts.
 *
 * @author murza
 */
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public void createPost(final CreatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        if (!request.getText().isBlank()) {
            postService.createPost(request, (User) userDetails);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable final Long id, @AuthenticationPrincipal UserDetails userDetails) {
        postService.deletePost(id, (User) userDetails);
    }

    @PatchMapping
    public void updatePost(final UpdatePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        if (!request.getText().isBlank()) {
            postService.updatePost(request, (User) userDetails);
        }
    }

    @PostMapping
    public void complainAboutPost(final ComplainPostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        if (!request.getComplain().isBlank()) {
            postService.complainAboutPost(request, (User) userDetails);
        }
    }

    @PutMapping
    public void votePost(final VotePostRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        postService.votePost(request, (User) userDetails);
    }
}
