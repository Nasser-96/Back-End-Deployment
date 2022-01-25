package com.example.MyInterests.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private  CommentService commentService;


    @GetMapping
    public List<Comment> getComments(){
        return commentService.getComments();
    }

    @PostMapping
    public ResponseEntity<String> addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable String id)
    {
        commentService.deleteComment(id);
    }
}
