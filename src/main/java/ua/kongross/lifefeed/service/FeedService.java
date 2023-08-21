package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.FeedDto;

public interface FeedService {
    FeedDto getFeed(User user);

    FeedDto getFeed(Long id, User user);

    FeedDto getSubbedFeed(User user);

    FeedDto getGlobalFeed(User user);
}
