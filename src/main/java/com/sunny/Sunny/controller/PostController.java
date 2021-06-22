package com.sunny.Sunny.controller;

import com.sunny.Sunny.entity.Post;

import com.sunny.Sunny.exception.ResourceNotFoundException;
import com.sunny.Sunny.model.request.CreateNewPostRequest;
import com.sunny.Sunny.model.request.UpdatePostRequest;
import com.sunny.Sunny.model.dto.PostDto;
import com.sunny.Sunny.model.mapper.PostMapper;
import com.sunny.Sunny.service.PostService;
import com.sunny.Sunny.service.Impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/search-post-by-title")
    public ResponseEntity<?> searchPostByTitle(@RequestParam("title") String title,
                                               @RequestParam("_page") Integer _page,
                                               @RequestParam("_limit") Integer _limit) {
        List<Post> posts = postService.searchPostByTitle(title, _page, _limit);
        if (posts == null) {
            throw new ResourceNotFoundException("Post with title: " + title + " not found");
        }

        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(PostMapper.toPostDto(post));
        }

        Integer _totalRows = postService.countRowByTitle(title);

        Map<Object, Object> pagination = new HashMap<>();
        pagination.put("_page", _page);
        pagination.put("_limit", _limit);
        pagination.put("_totalRows", _totalRows);

        Map<Object, Object> repsonse = new HashMap<>();
        repsonse.put("data", postDtos);
        repsonse.put("pagination", pagination);

        return ResponseEntity.ok(repsonse);
    }

    @GetMapping("/search-post-by-description")
    public ResponseEntity<?> searchPostByDescription(@RequestParam("description") String description,
                                                     @RequestParam("_page") Integer _page,
                                                     @RequestParam("_limit") Integer _limit) {
        List<Post> posts = postService.searchPostByDescription(description, _page, _limit);
        if (posts == null) {
            throw new ResourceNotFoundException("Post with description: " + description + " not found");
        }
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            postDtos.add(PostMapper.toPostDto(post));
        }

        Integer _totalRows = postService.countRowByDescription(description);

        Map<Object, Object> pagination = new HashMap<>();
        pagination.put("_page", _page);
        pagination.put("_limit", _limit);
        pagination.put("_totalRows", _totalRows);

        Map<Object, Object> response = new HashMap<>();
        response.put("data", postDtos);
        response.put("pagination", pagination);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-post-by-id")
    public ResponseEntity<?> deletePostById(@RequestParam("id") int id) {
        Post post = postService.deletePostById(id);
        if (post == null) {
            throw new ResourceNotFoundException("Cannot delete post id: " + id);
        }
        return ResponseEntity.ok(PostMapper.toPostDto(post));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewPost(@AuthenticationPrincipal UserDetailsImpl details,
                                           @RequestBody CreateNewPostRequest createNewPostRequest) {
        Post post = postService.createNewPost(details.getUserDTO().getId(), createNewPostRequest);
        return ResponseEntity.ok(PostMapper.toPostDto(post));
    }

    @PutMapping("/update-post-by-id")
    public ResponseEntity<?> updatePostById(@RequestBody UpdatePostRequest updatePostRequest) {
        Post post = postService.updatePostById(updatePostRequest);
        return ResponseEntity.ok(PostMapper.toPostDto(post));
    }
}
