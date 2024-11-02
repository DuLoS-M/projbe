package com.example.projbe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.projbe.DTOs.OrderDTO;
import com.example.projbe.entity.Orders;
import com.example.projbe.enums.OrderStatus;
import com.example.projbe.service.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return ordersService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrdersByUserId(@PathVariable Long userId) {
        return ordersService.getOrdersByUserId(userId);
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody Orders order, @RequestParam Long userId) {
        return ordersService.createOrder(order, userId);
    }

     @PutMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String statusStr = body.get("status");
        if (statusStr == null) {
            return ResponseEntity.badRequest().body(null);
        }
        OrderStatus status;
        try {
            status = OrderStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            
            OrderDTO updatedOrder = ordersService.updateOrderStatus(id, status);
            if (updatedOrder != null) {
                return ResponseEntity.ok(updatedOrder);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough ingredients in stock");
        }}
    

    @PutMapping("/{id}")
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody Orders orderDetails) {
        return ordersService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        ordersService.deleteOrder(id);
    }
}