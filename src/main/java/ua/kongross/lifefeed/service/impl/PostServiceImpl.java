package ua.kongross.lifefeed.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Post;
import ua.kongross.lifefeed.database.repository.PostRepository;
import ua.kongross.lifefeed.database.repository.UserRepository;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.FeedDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository; //todo remove

    @Override
    public FeedDto getPosts() {
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByOrderByCreatedAtAsc());

        ArrayList<FeedDto.FeedPost> feedPosts = posts.stream()
                .map(post -> FeedDto.FeedPost.builder()
                        .id(post.getId())
                        .text(post.getText())
                        .createdAt(post.getCreatedAt())
                        .authorUsername(post.getAuthor().getUsername())
                        .authorId(post.getAuthor().getId())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));

        return FeedDto.builder()
                .posts(feedPosts)
                .build();
    }

    @Override
    public void createPost(CreatePostRequest request) {
        Post post = new Post();
        post.setText(request.getText());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(userRepository.findUserByUsername("username").get()); //todo remove

        postRepository.save(post);
    }
}
