package com.example.redis.dao;


import com.example.redis.domain.OrderDetailEntity;
import com.example.redis.domain.ProductEntity;
import com.example.redis.dto.ProductDTO;
import com.example.redis.repository.OrderDetailRepository;
import com.example.redis.repository.ProductRepository;
import com.example.redis.request.OrderProductRequest;
import com.example.redis.response.FindUserOrderResponse;
import com.example.redis.response.ProductOrderDetailRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailDao {
    @Autowired
    private OrderDetailRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public void addNewOrderDetailDao(Long orderId, List<OrderProductRequest> orderDetail){
        orderDetail.forEach(x->{
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setProductId(x.getProId());
            orderDetailEntity.setUserOrderId(orderId);
            orderDetailEntity.setQty(x.getQty());
            repository.saveAndFlush(orderDetailEntity);
        });
    }

    public FindUserOrderResponse findByOrderIdDao(Long orderId){
        List<OrderDetailEntity> orderDetailEntities = repository.findByUserOrderId(orderId);
        double totalPrice = orderDetailEntities.stream().mapToDouble(x->{
            ProductEntity productEntity = productRepository.findById(x.getProductId()).orElse(null);
            if(productEntity != null){
                return productEntity.getUnitPrice() * x.getQty();
            }
            return 0;

        }).sum();
        FindUserOrderResponse findUserOrderResponse = new FindUserOrderResponse();
        findUserOrderResponse.setTotalPrice(totalPrice);
        findUserOrderResponse.setListOrder(orderDetailEntities.stream().map(x->{
            ProductOrderDetailRes productOrderDetailRes = new ProductOrderDetailRes();
            ProductEntity productEntity = productRepository.findById(x.getProductId()).orElse(null);
            ProductDTO productDTO = ProductDTO.transferObject(productEntity,ProductDTO.class);
            productOrderDetailRes.setProductDTO(productDTO);
            productOrderDetailRes.setQty(x.getQty());
            return productOrderDetailRes;
        }).collect(Collectors.toList()));

        return findUserOrderResponse;

    }

    public List<OrderDetailEntity> findByOrderId(Long orderId) {
        return repository.findByUserOrderId(orderId);
    }

    public void updateOrderDetailDao(Long orderId, Long proId, int qty) {
        OrderDetailEntity orderDetailEntity = repository.findByProductIdAndUserOrderId(proId,orderId).orElse(null);
        if(orderDetailEntity != null){
            OrderDetailEntity orderDetailEntity1 = repository.getOne(orderDetailEntity.getId());
            orderDetailEntity1.setQty(qty);
            repository.saveAndFlush(orderDetailEntity1);
        }
    }

    public void removeItemOrderDetail(Long orderId, List<Long> proIds) {
        proIds.forEach(x->{
            repository.findByProductIdAndUserOrderId(x, orderId).ifPresent(orderDetailEntity -> repository.delete(orderDetailEntity));
        });

    }
}
