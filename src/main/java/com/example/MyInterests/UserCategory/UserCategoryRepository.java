package com.example.MyInterests.UserCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory,Long> {
    List<UserCategory> findByUserId(Long id);

}
