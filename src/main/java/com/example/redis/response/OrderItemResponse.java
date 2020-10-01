package com.example.redis.response;

import com.example.redis.domain.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OrderItemResponse implements Serializable {
    private ProductEntity productEntity;
    private int qty;
}
