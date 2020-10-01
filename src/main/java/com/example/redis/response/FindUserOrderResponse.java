package com.example.redis.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindUserOrderResponse {
    private List<ProductOrderDetailRes> listOrder;
    private double totalPrice;
}
