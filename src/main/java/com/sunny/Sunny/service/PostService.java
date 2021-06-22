package com.sunny.Sunny.service;

import com.sunny.Sunny.entity.Post;
import com.sunny.Sunny.model.request.CreateNewPostRequest;
import com.sunny.Sunny.model.request.UpdatePostRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> searchPostByTitle(String searchTitle, Integer _page, Integer _limit);
    List<Post> searchPostByDescription(String searchDescription, Integer _page, Integer _limit);
    Post deletePostById(Integer id);
    Post createNewPost(Integer user_id, CreateNewPostRequest createNewPostRequest);
    Post updatePostById(UpdatePostRequest updatePostRequest);
    Integer countRowByTitle(String title);
    Integer countRowByDescription(String description);

}
