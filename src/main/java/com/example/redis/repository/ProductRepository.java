package com.example.redis.repository;

import com.example.redis.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findById(Long id);

    List<ProductEntity> findByProductNameContaining(String productName);

    @Query("select p from ProductEntity p where p.productName = :productName")
    List<ProductEntity> findByProductNameQuery(@Param("productName") String name);
}
