package com.example.redis.service;

import com.example.redis.dao.OrderDao;
import com.example.redis.domain.UserOrderEntity;
import com.example.redis.request.OrderRequest;
import com.example.redis.request.UpdateOrderReq;
import com.example.redis.response.FindUserOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailService orderDetailService;

    public UserOrderEntity addNewOrderService(OrderRequest request){
        UserOrderEntity userOrderEntity = orderDao.addNewOrderDao(request.getUserId());
        orderDetailService.addNewOrderDetailService(userOrderEntity.getId(), request.getListOrder());
        return userOrderEntity;
    }

    public FindUserOrderResponse findOrderUserService(Long userId, Long orderId) {
        UserOrderEntity userOrderEntity = orderDao.findUserOrderById(userId, orderId);
        if(userOrderEntity != null){
            return orderDetailService.findByOrderIdService(userOrderEntity.getId());
        }else{
            return null;
        }
    }

    public FindUserOrderResponse findOrderUserService(Long orderId) {
        UserOrderEntity userOrderEntity = orderDao.findUserOrderById(orderId);
        if(userOrderEntity != null){
            return orderDetailService.findByOrderIdService(userOrderEntity.getId());
        }else{
            return null;
        }
    }

    public FindUserOrderResponse updateOrderService(UpdateOrderReq req) {
        UserOrderEntity userOrderEntity = orderDao.findUserOrderById(req.getOrderId());
        if(userOrderEntity != null){
            orderDetailService.updateOrderDetail(req);
            return findOrderUserService(req.getOrderId());
        }
        return null;

    }

    public FindUserOrderResponse removeItemOrderService(Long orderId, List<Long> proIds) {
        UserOrderEntity userOrderEntity = orderDao.findUserOrderById(orderId);
        if(userOrderEntity != null){
            orderDetailService.removeItemOrderDetail(orderId,proIds);
            return orderDetailService.findByOrderIdService(userOrderEntity.getId());
        }
        return null;
    }

}
