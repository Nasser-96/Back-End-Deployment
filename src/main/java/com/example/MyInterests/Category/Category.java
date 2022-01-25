package com.example.MyInterests.Category;

import com.example.MyInterests.Post.Post;
import com.example.MyInterests.UserCategory.UserCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String category;

    @JsonIgnoreProperties("category")
    @OneToMany(mappedBy = "category")
    private List<Post> postsC;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<UserCategory> categories;

    public Category(Long id, String category, List<Post> postsC, List<UserCategory> categories) {
        this.id = id;
        this.category = category;
        this.postsC = postsC;
        this.categories = categories;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Post> getPostsC() {
        return postsC;
    }

    public void setPostsC(List<Post> postsC) {
        this.postsC = postsC;
    }

    public List<UserCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<UserCategory> categories) {
        this.categories = categories;
    }
}
