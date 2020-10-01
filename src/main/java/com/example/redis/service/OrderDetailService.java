package com.example.redis.service;


import com.example.redis.dao.OrderDetailDao;
import com.example.redis.domain.OrderDetailEntity;
import com.example.redis.request.OrderProductRequest;
import com.example.redis.request.UpdateOrderReq;
import com.example.redis.response.FindUserOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    public void addNewOrderDetailService(Long orderId, List<OrderProductRequest> orderDetail){
        orderDetailDao.addNewOrderDetailDao(orderId, orderDetail);
    }

    public List<OrderDetailEntity> findByOrderId(Long orderId){
        return orderDetailDao.findByOrderId(orderId);
    }

    public FindUserOrderResponse findByOrderIdService(Long orderId){
        return orderDetailDao.findByOrderIdDao(orderId);
    }

    public void updateOrderDetail(UpdateOrderReq req) {
        req.getProductOrders().forEach(x->{
            orderDetailDao.updateOrderDetailDao(req.getOrderId(), x.getProId(),x.getQty());
        });
    }

    public void removeItemOrderDetail(Long orderId,List<Long> proIds) {
        proIds.forEach(x->{
            orderDetailDao.removeItemOrderDetail(orderId,proIds);
        });
    }

}
