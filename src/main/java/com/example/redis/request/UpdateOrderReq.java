package com.example.redis.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderReq {
    private Long orderId;
    private List<ChangeProductOrder>  productOrders;
}
