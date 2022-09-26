package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetails;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.FeedDto;

public interface PostService {
    FeedDto getPosts();

    void createPost(CreatePostRequest request, UserDetails userDetails);
}
