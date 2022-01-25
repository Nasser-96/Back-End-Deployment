package com.example.MyInterests.Post;


import static org.junit.jupiter.api.Assertions.*;
import com.example.MyInterests.User.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostRepositoryTest {

    private final PostRepository postRepository;

    @Autowired
    public PostRepositoryTest(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    @Test
    void  itShouldDeletePost(){
        Post post = new Post(50L,"Caption","Image");
        Post savedPost = postRepository.save(post);

        postRepository.deleteById(savedPost.getId());
        Post result = postRepository.findById(post.getId()).orElse(null);
        assertNull(result);
    }

    @Test
    void  itShouldUpdatePost(){
        Post post = new Post(50L,"Caption","Image");
        Post savedUser = postRepository.save(post);
        Post result1 = postRepository.findById(savedUser.getId()).orElse(null);

        assertEquals(result1.getCaption(),"Caption");


        Post post2 = new Post("Caption2", "Image2");
        if ( savedUser!=null){
            savedUser.setCaption(post2.getCaption());
            savedUser.setImage(post2.getCaption());
            postRepository.save(savedUser);
        }

        Post result = postRepository.findById(savedUser.getId()).orElse(null);

        assertEquals(result.getCaption(),"Caption2");
    }
}
