package com.example.redis.rest;

import com.example.redis.dto.CategoryDTO;
import com.example.redis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/post/add_new_category")
    public ResponseEntity<?> addNewCategoryController(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.addNewCategoryService(categoryDTO));
    }

    @GetMapping("/get/find_by_category_name")
    public ResponseEntity<?> findCategoryByName(@RequestParam("name") String name){
        return ResponseEntity.ok(categoryService.findCategoryByName(name));
    }
}
