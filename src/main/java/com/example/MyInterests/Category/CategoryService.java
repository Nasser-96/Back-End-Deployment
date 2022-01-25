package com.example.MyInterests.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){return categoryRepository.findAll();}

    public Category getCategory(String id){
        Long category_id = Long.parseLong(id);
        return categoryRepository.findById(category_id).orElse(null);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
}
