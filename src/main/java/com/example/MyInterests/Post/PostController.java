package com.example.MyInterests.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable String id){
        return postService.getPost(id);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        postService.deletePost(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable String id, @RequestBody Post data){
        postService.updatePost(id, data);
    }

}
