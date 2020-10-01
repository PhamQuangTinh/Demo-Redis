package com.example.redis.response;

import com.example.redis.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderDetailRes {
    private ProductDTO productDTO;
    private int qty;
}
