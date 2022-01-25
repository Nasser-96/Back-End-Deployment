package com.example.MyInterests.Comment;

import com.example.MyInterests.Post.Post;
import com.example.MyInterests.Post.PostRepository;
import com.example.MyInterests.User.User;
import com.example.MyInterests.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public ResponseEntity<String> addComment(Comment comment){
        Long post_id = comment.getPost().getId();
        Post post = postRepository.getById(post_id);

        Long user_id = comment.getUser().getId();
        User user = userRepository.getById(user_id);
        if (post !=null && user != null){
            comment.setPost(post);
            comment.setUser(user);
            commentRepository.save(comment);
            return ResponseEntity.ok().body("Saved Successfully");
        }
        else {
            return ResponseEntity.badRequest().body("Not Saved");
        }
    }

    public void deleteComment(String id){
        Long comment_id = Long.parseLong(id);
        commentRepository.deleteById(comment_id);
    }
}
