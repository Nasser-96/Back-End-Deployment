package com.example.MyInterests.UserCategory;

import com.example.MyInterests.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("follow")
@CrossOrigin("*")
public class UserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;

    @GetMapping("/{id}")
    public List<UserCategory> getUserFollows(@PathVariable String id){
        return userCategoryService.getUserFollows(id);
    }

    @PostMapping
    public ResponseEntity<?> followCategory(@RequestBody UserCategory userCategory){
        return userCategoryService.flowCategory(userCategory);
    }

    @DeleteMapping("/{id}")
    public void UnFollow(@PathVariable String id){
        userCategoryService.UnFollow(id);
    }
}
