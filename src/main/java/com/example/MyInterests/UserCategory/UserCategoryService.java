package com.example.MyInterests.UserCategory;

import com.example.MyInterests.Post.Post;
import com.example.MyInterests.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService {
    @Autowired
    private UserCategoryRepository userCategoryRepository;
    @Autowired
    private UserRepository userRepository;

    public List<UserCategory> getUserFollows(String id){
        Long user_id = Long.parseLong(id);
        return userCategoryRepository.findByUserId(user_id);
    }

    public ResponseEntity<?> flowCategory(UserCategory userCategory){
        List<UserCategory> userCategory1 = userCategoryRepository.findAll();
        for (int i=0 ; i<userCategory1.size();i++){
            if (userCategory1.get(i).getUser().getId() == userCategory.getUser().getId() && userCategory1.get(i).getCategory().getId() == userCategory.getCategory().getId()){
                return ResponseEntity.badRequest().body("You Already Follow this Category");
            }
        }
        return ResponseEntity.ok().body(userCategoryRepository.save(userCategory));
    }

    public void UnFollow(String id){
        Long follow_id = Long.parseLong(id);
        userCategoryRepository.deleteById(follow_id);
    }
}
