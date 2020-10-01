package com.example.redis.dao;


import com.example.redis.domain.UserOrderEntity;
import com.example.redis.repository.OrderRepository;
import com.example.redis.request.OrderProductRequest;
import com.example.redis.response.OrderItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDao {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderDetailDao orderDetailDao;

    public UserOrderEntity addNewOrderDao(Long userId){
        UserOrderEntity userOrderEntity = new UserOrderEntity();
        userOrderEntity.setUserId(userId);
        return repository.saveAndFlush(userOrderEntity);
    }

    public UserOrderEntity findUserOrderById(Long userId, Long orderId){
        return repository.findByIdAndUserId(orderId,userId).orElse(null);
    }

    public UserOrderEntity findUserOrderById(Long orderId){
        return repository.findById(orderId).orElse(null);
    }

    public void adCartToOrder(Long userId, List<OrderProductRequest> carts) {
        UserOrderEntity userOrderEntity = addNewOrderDao(userId);
        orderDetailDao.addNewOrderDetailDao(userOrderEntity.getId(), carts );
    }
}
