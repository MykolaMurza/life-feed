package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetails;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.FeedDto;

import java.util.UUID;

public interface PostService {
    FeedDto getPosts();

    void createPost(CreatePostRequest request, UserDetails userDetails);

    void deletePost(Long id, UserDetails userDetails);
}
