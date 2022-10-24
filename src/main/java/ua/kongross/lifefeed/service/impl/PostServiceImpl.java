package ua.kongross.lifefeed.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Post;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.PostRepository;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.service.UserService;
import ua.kongross.lifefeed.web.dto.FeedDto;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
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
    public void createPost(CreatePostRequest request, User user) {
        Post post = new Post();
        post.setText(request.getText());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(user);
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void deletePost(Long id, User user) {
        postRepository.deleteAllByIdAndAuthor(id, user);
    }

    @Override
    public FeedDto getProfilePosts(Long id, User user) {
        User userById = userService.findUserById(id);
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByAuthorOrderByCreatedAtDesc(userById));

        ArrayList<FeedDto.FeedPost> feedPosts = getPosts(posts, user.getId());

        return FeedDto.builder().posts(feedPosts).build();
    }
}
