package com.example.redis.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderProductRequest {
    private Long proId;
    private int qty;
}
