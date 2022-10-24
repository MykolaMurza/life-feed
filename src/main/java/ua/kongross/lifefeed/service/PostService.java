package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.FeedDto;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

public interface PostService {
    FeedDto getPosts(User user);

    void createPost(CreatePostRequest request, User user);

    void deletePost(Long id, User user);

    FeedDto getProfilePosts(Long id, User user);
}
