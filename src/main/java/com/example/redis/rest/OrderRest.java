package com.example.redis.rest;


import com.example.redis.request.*;
import com.example.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderRest {
    @Autowired
    private OrderService orderService;

    @PostMapping("/post/create_new_order")
    public ResponseEntity<?> addNewOrderController(@RequestBody OrderRequest request){
        return ResponseEntity.ok(orderService.addNewOrderService(request));
    }


    @GetMapping("/get/get_user_order")
    public ResponseEntity<?> findUserOrder(@RequestParam("userId") Long userId, @RequestParam("orderId") Long orderId){
        return ResponseEntity.ok(orderService.findOrderUserService(userId, orderId));
    }

    @PostMapping("/post/update_order")
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderReq req){
        return ResponseEntity.ok(orderService.updateOrderService(req));
    }

    @PostMapping("/post/delete_item_order")
    public ResponseEntity<?> removeItemOrder(@RequestBody RemoveItemOrder item){
        return ResponseEntity.ok((orderService.removeItemOrderService(item.getOrderId(),item.getProIds())));
    }

    @PostMapping("/post/add_product_to_cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductToCartReq req){
        return ResponseEntity.ok(orderService.addProductToCartService(req));
    }

    @GetMapping("/get/get_cart_items")
    public ResponseEntity<?> getCartItems(@RequestParam("user_id") Long userId){
        return ResponseEntity.ok(orderService.getListOrderProduct(userId));
    }

    @PostMapping("/post/paying")
    public ResponseEntity<?> orderProductFromCart(@RequestBody OrderPayingRequest req){
        return ResponseEntity.ok(orderService.orderProductFromCart(req.getUserId()));
    }

}
