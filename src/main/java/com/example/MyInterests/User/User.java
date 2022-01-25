package com.example.MyInterests.User;


import com.example.MyInterests.Comment.Comment;
import com.example.MyInterests.Post.Post;
import com.example.MyInterests.Role.Role;
import com.example.MyInterests.UserCategory.UserCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(unique = true)
    private String userName ;
    private String password ;
    @Column(unique = true)
    private String email ;
    private String moreInfo ;
    private String personalImg;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    @JsonIgnore
    private List<UserCategory> categories;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnoreProperties("comments")
    private List<Comment> comments;


    public User(Long id, String userName, String password, String email, String moreInfo, String personalImg, List<Post> posts, List<Role> roles, List<UserCategory> categories, List<Comment> comments) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.moreInfo = moreInfo;
        this.personalImg = personalImg;
        this.posts = posts;
        this.roles = roles;
        this.categories = categories;
        this.comments = comments;
    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getPersonalImg() {
        return personalImg;
    }

    public void setPersonalImg(String personalImg) {
        this.personalImg = personalImg;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<UserCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<UserCategory> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
