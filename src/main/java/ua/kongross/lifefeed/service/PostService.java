package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.web.dto.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.FeedDto;

public interface PostService {
    FeedDto getPosts();

    void createPost(CreatePostRequest request);
}