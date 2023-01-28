package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.FeedDto;

public interface FeedService {
    FeedDto getPosts(User user);

    FeedDto getProfilePosts(Long id, User user);
}
