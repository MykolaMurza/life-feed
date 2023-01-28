package ua.kongross.lifefeed.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.kongross.lifefeed.database.entity.Post;
import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.database.repository.PostRepository;
import ua.kongross.lifefeed.service.PostService;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
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
    public void updatePost(CreatePostRequest request, User userDetails) {

    }
}
