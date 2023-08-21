package ua.kongross.lifefeed.service;

import ua.kongross.lifefeed.database.entity.User;
import ua.kongross.lifefeed.web.dto.PostDto;
import ua.kongross.lifefeed.web.dto.request.ComplainPostRequest;
import ua.kongross.lifefeed.web.dto.request.CreatePostRequest;
import ua.kongross.lifefeed.web.dto.request.UpdatePostRequest;
import ua.kongross.lifefeed.web.dto.request.VotePostRequest;

public interface PostService {
    PostDto getPost(Long id, User user);

    void createPost(CreatePostRequest request, User user);

    void deletePost(Long id, User user);

    void updatePost(UpdatePostRequest request, User userDetails);

    void complainAboutPost(ComplainPostRequest request, User userDetails);

    void votePost(VotePostRequest request, User userDetails);

    void share(Long id, User userDetails);
}
