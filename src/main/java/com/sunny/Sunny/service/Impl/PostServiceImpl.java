package com.sunny.Sunny.service.Impl;

import com.sunny.Sunny.entity.Category;
import com.sunny.Sunny.entity.Post;
import com.sunny.Sunny.exception.ResourceNotFoundException;
import com.sunny.Sunny.model.request.CreateNewPostRequest;
import com.sunny.Sunny.model.request.UpdatePostRequest;
import com.sunny.Sunny.repository.CategoryRepository;
import com.sunny.Sunny.repository.PostRepository;
import com.sunny.Sunny.repository.UserRepository;
import com.sunny.Sunny.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class    PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> searchPostByTitle(String searchTitle, Integer _page, Integer _limit) {
        List<Post> posts = (List<Post>)postRepository.searchPostByTitle(searchTitle, _page, _limit);
        if(posts.isEmpty()){
            return null;
        }

        return posts;
    }

    @Override
    public List<Post> searchPostByDescription(String searchDescription, Integer _page, Integer _limit) {
        List<Post> posts = ( List<Post>)postRepository.searchPostByDescription(searchDescription, _page, _limit);
        if(posts.isEmpty()){
            return null;
        }

        return posts;
    }

    @Override
    public Post deletePostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Delete with category id: " + id + " not success"));
        postRepository.delete(post);
        return post;
    }

    @Override
    public Post createNewPost(Integer userId, CreateNewPostRequest postRequest) {
        Category category = categoryRepository.findById(postRequest.getCategorize_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category ID " + postRequest.getCategorize_id()));
        return userRepository.findById(userId).map(user ->
        {
            Post post = new Post(postRequest.getTitle(),
                    postRequest.getDescription(),
                    postRequest.getPrice(),
                    postRequest.getSize(),
                    category,
                    user);
            postRepository.save(post);
            return post;
        }).orElseThrow(() -> new ResourceNotFoundException("User Id " + userId + " not found"));
    }

    @Override
    public Post updatePostById(UpdatePostRequest updatePostRequest) {
        Post post = postRepository.findById(updatePostRequest.getId())
                .orElseThrow(()-> new ResourceNotFoundException("Post Id " + updatePostRequest.getCategorize_id() + " not found"));
        post.setTitle(updatePostRequest.getTitle());
        post.setDescription(updatePostRequest.getDescription());
        post.setPrice(updatePostRequest.getPrice());
        post.setSize(updatePostRequest.getSize());
        post.setCategory(this.categoryRepository.findById(updatePostRequest.getCategorize_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category Id " + updatePostRequest.getCategorize_id() + " not found")));
        postRepository.save(post);
        return post;
    }

    @Override
    public Integer countRowByTitle(String title) {
        return postRepository.countRowByTitle(title);
    }

    @Override
    public Integer countRowByDescription(String description) {
        return postRepository.countRowByDescription(description);
    }
}