package com.example.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends MainDTO<ProductDTO> {
    private Long id;

    private String productName;

    private double unitPrice;

    private Long categoryId;

}
