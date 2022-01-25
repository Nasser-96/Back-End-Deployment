package com.example.MyInterests.Post;

import com.example.MyInterests.Category.Category;
import com.example.MyInterests.Category.CategoryRepository;
import com.example.MyInterests.User.User;
import com.example.MyInterests.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public ResponseEntity<?> createPost(Post post){
        Long user_id = post.getUser().getId();
        User user = userRepository.getById(user_id);

        Long category_id = post.getCategory().getId();
        Category category = categoryRepository.getById(category_id);
        if (user !=null){
            post.setUser(user);
            return ResponseEntity.ok().body(postRepository.save(post));
        }
        else {
            return ResponseEntity.badRequest().body("Not Saved");
        }
    }
    public Post getPost(String id){
        Long post_id = Long.parseLong(id);
        return postRepository.findById(post_id).orElse(null);
    }

    public void updatePost(String id, Post data){
        Long post_id = Long.parseLong(id);
        Post post = postRepository.findById(post_id).orElse(null);
        if ( post!=null){
            post.setCaption(data.getCaption());
            post.setImage(data.getImage());
            postRepository.save(post);
        }
    }

    public void deletePost(String id){
        Long post_id = Long.parseLong(id);
        postRepository.deleteById(post_id);
    }
}
