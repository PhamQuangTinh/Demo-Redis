package com.example.redis.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AddProductToCartReq {
    private Long userId;
    private Long proId;
    private int qty;
}
