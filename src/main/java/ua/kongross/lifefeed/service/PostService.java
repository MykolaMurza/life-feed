package ua.kongross.lifefeed.service;

import org.springframework.security.core.userdetails.UserDetails;
import ua.kongross.lifefeed.web.dto.FeedDto;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

public interface PostService {
    FeedDto getPosts();

    void createPost(CreatePostRequest request, UserDetails userDetails);

    void deletePost(Long id, UserDetails userDetails);

    FeedDto getProfilePosts(Long id);
}
