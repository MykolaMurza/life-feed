package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

public interface PostService {
    void createPost(CreatePostRequest request, User user);

    void deletePost(Long id, User user);

    void updatePost(CreatePostRequest request, User userDetails);
}
