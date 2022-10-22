package ua.kongross.lifefeed.service.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public FeedDto getPosts() {
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByOrderByCreatedAtDesc());

        ArrayList<FeedDto.FeedPost> feedPosts = getPosts(posts);

        return FeedDto.builder().posts(feedPosts).build();
    }

    @Override
    public void createPost(CreatePostRequest request, UserDetails userDetails) {
        Post post = new Post();
        post.setText(request.getText());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor((User) userDetails);
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void deletePost(Long id, UserDetails userDetails) {
        postRepository.deleteAllByIdAndAuthor(id, (User) userDetails);
    }

    @Override
    public FeedDto getProfilePosts(Long id) {
        User userById = userService.findUserById(id);
        ArrayList<Post> posts = Lists.newArrayList(postRepository.findByAuthorOrderByCreatedAtDesc(userById));

        ArrayList<FeedDto.FeedPost> feedPosts = getPosts(posts);

        return FeedDto.builder().posts(feedPosts).build();
    }

    private static ArrayList<FeedDto.FeedPost> getPosts(ArrayList<Post> posts) {
        return posts.stream()
                .map(post -> FeedDto.FeedPost
                        .builder()
                        .id(post.getId())
                        .text(post.getText())
                        .createdAt(post.getCreatedAt())
                        .authorUsername(post.getAuthor().getUsername())
                        .authorId(post.getAuthor().getId())
                        .build())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
