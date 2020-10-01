package com.example.redis.service;


import com.example.redis.dao.ProductDao;
import com.example.redis.domain.ProductEntity;
import com.example.redis.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public ProductEntity addNewProductService(ProductDTO productDTO){
        return productDao.addNewProductDao(productDTO);
    }

    public List<ProductDTO> findByProductName(String name) {
        return productDao.findByProductName(name);
    }

    public ProductEntity findByProductId(Long proId) {
        return productDao.findProductById(proId);
    }
}
