package com.example.redis.dao;


import com.example.redis.domain.CategoryEntity;
import com.example.redis.domain.ProductEntity;
import com.example.redis.dto.ProductDTO;
import com.example.redis.repository.CategoryRepository;
import com.example.redis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDao {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductEntity addNewProductDao(ProductDTO productDTO){
        CategoryEntity categoryEntity = categoryRepository.findById(productDTO.getCategoryId()).orElse(null);
        if(categoryEntity != null){
            ProductEntity productEntity = ProductDTO.transferObject(productDTO, ProductEntity.class);
            productEntity.setId(null);
            return productRepository.saveAndFlush(productEntity);
        }
        return null;

    }

    public List<ProductDTO> findByProductName(String name) {
        List<ProductEntity> listProducts = productRepository.findByProductNameContaining(name);
        return listProducts.stream().map(x->ProductDTO.transferObject(x,ProductDTO.class)).collect(Collectors.toList());


    }

    public ProductEntity findProductById(Long proId) {
        return productRepository.findById(proId).orElse(null);

    }
}
