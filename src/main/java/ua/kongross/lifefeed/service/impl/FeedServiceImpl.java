package ua.kongross.lifefeed.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Post;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.PostRepository;
import ua.kongross.lifefeed.service.FeedService;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.FeedDto;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    private final PostRepository postRepository;
    private final UserService userService;

    private static ArrayList<FeedDto.FeedPost> getPosts(ArrayList<Post> posts, Long id) {
        return posts.stream()
                .map(post -> FeedDto.FeedPost
                        .builder()
                        .id(post.getId())
                        .text(post.getText())
                        .createdAt(post.getCreatedAt())
                        .authorUsername(post.getAuthor().getUsername())
                        .authorId(post.getAuthor().getId())
                        .removable(post.getAuthor().getId().equals(id))
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public FeedDto getPosts(User user) {
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByOrderByCreatedAtDesc());

        ArrayList<FeedDto.FeedPost> feedPosts = getPosts(posts, user.getId());

        return FeedDto.builder().posts(feedPosts).build();
    }

    @Override
    public FeedDto getProfilePosts(Long id, User user) {
        User userById = userService.findUserById(id);
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByAuthorOrderByCreatedAtDesc(userById));

        ArrayList<FeedDto.FeedPost> feedPosts = getPosts(posts, user.getId());

        return FeedDto.builder().posts(feedPosts).build();
    }
}
