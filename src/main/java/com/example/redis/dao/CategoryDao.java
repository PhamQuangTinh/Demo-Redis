package com.example.redis.dao;


import com.example.redis.domain.CategoryEntity;
import com.example.redis.dto.CategoryDTO;
import com.example.redis.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDao {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity addNewCategoryDao(CategoryDTO categoryDTO){
        return categoryRepository.saveAndFlush(CategoryDTO.transferObject(categoryDTO, CategoryEntity.class));
    }

    public List<CategoryDTO> findCategoryByName(String name) {
        List<CategoryEntity> listEntity = categoryRepository.findByCategoryNameQuery(name);
        return listEntity.stream().map(x-> CategoryDTO.transferObject(x,CategoryDTO.class)).collect(Collectors.toList());

    }
}
