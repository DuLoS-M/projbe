package com.example.projbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projbe.entity.OrderDetails;
import com.example.projbe.service.OrderDetailsService;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @GetMapping("/{id}")
    public OrderDetails getOrderDetailsById(@PathVariable Long id) {
        return orderDetailsService.getOrderDetailsById(id);
    }

    @PostMapping
    public OrderDetails createOrderDetails(@RequestBody OrderDetails orderDetails) {
        return orderDetailsService.createOrderDetails(orderDetails);
    }

    @PutMapping("/{id}")
    public OrderDetails updateOrderDetails(@PathVariable Long id, @RequestBody OrderDetails orderDetailsDetails) {
        return orderDetailsService.updateOrderDetails(id, orderDetailsDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetails(@PathVariable Long id) {
        orderDetailsService.deleteOrderDetails(id);
    }
}