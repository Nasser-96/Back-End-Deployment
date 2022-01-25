package com.example.MyInterests.Post;

import com.example.MyInterests.Category.Category;
import com.example.MyInterests.Comment.Comment;
import com.example.MyInterests.User.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String caption;
    private String image;


    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("posts")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("postsC")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @Column(nullable = false)
    private Date date;


    public Post(Long id, String caption, String image, User user, Category category, List<Comment> comments, Date date) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.user = user;
        this.category = category;
        this.comments = comments;
        this.date = date;
    }

    public Post() {
    }

    public Post(String caption, String image) {
        this.caption = caption;
        this.image = image;
    }

    public Post(Long id, String caption, String image) {
        this.id = id;
        this.caption = caption;
        this.image = image;
    }

    @PrePersist
    protected void onCreate() {
        date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getTimestamp() {
        return date;
    }
}
