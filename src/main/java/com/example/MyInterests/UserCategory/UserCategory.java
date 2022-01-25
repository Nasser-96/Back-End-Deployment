package com.example.MyInterests.UserCategory;

import com.example.MyInterests.Category.Category;
import com.example.MyInterests.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user_category")
@Table(name = "user_category")
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties({"posts","categories","categories"})
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties({"posts","categories","categories"})
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Category category;

    public UserCategory(Long id, @Nullable User user, @Nullable Category category) {
        this.id = id;
        this.user = user;
        this.category = category;
    }

    public UserCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public User getUser() {
        return user;
    }

    public void setUser(@Nullable User user) {
        this.user = user;
    }

    @Nullable
    public Category getCategory() {
        return category;
    }

    public void setCategory(@Nullable Category category) {
        this.category = category;
    }
}
