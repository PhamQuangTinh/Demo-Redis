package com.example.redis.service;

import com.example.redis.dao.OrderDao;
import com.example.redis.domain.ProductEntity;
import com.example.redis.domain.UserOrderEntity;
import com.example.redis.dto.ProductDTO;
import com.example.redis.request.AddProductToCartReq;
import com.example.redis.request.OrderProductRequest;
import com.example.redis.request.OrderRequest;
import com.example.redis.request.UpdateOrderReq;
import com.example.redis.response.FindUserOrderResponse;
import com.example.redis.response.OrderItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String CART_ITEM = "CART_ITEM";
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

    public ProductDTO addProductToCartService(AddProductToCartReq req) {
        try{
            ProductEntity productEntity = productService.findByProductId(req.getProId());

            if(productEntity !=null){
                OrderItemResponse orderItemResponse = new OrderItemResponse(productEntity, req.getQty());
                List<OrderItemResponse> orderItemResponses = null;
                try{
                    orderItemResponses = (List<OrderItemResponse>)redisTemplate.opsForHash().get(CART_ITEM, req.getUserId());
                }catch (Exception e1){
                    orderItemResponses = new ArrayList<>();
                }
                if(orderItemResponses == null){
                    orderItemResponses = new ArrayList<>();
                }
                orderItemResponses.add(orderItemResponse);
                redisTemplate.opsForHash().put(CART_ITEM,req.getUserId(), orderItemResponses);
                return ProductDTO.transferObject(productEntity, ProductDTO.class);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public List<OrderItemResponse> getListOrderProduct(Long userId){
        Object list = redisTemplate.opsForHash().get(CART_ITEM,userId);
        return (List<OrderItemResponse>) list;
    }


    public double orderProductFromCart(Long userId) {
        List<OrderItemResponse> carts = getListOrderProduct(userId);
        double totalPricce = carts.stream().mapToDouble(x->{
            return x.getQty() * x.getProductEntity().getUnitPrice();
        }).sum();
        List<OrderProductRequest> listOrder = carts.stream().map(x->{
            return new OrderProductRequest(x.getProductEntity().getId(), x.getQty());
        }).collect(Collectors.toList());
        orderDao.adCartToOrder(userId, listOrder);
        redisTemplate.opsForHash().delete(CART_ITEM,userId);
        return totalPricce;
    }
}
