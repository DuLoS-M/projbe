package com.example.projbe.service;

import com.example.projbe.entity.OrderDetails;
import com.example.projbe.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsById(Long id) {
        return orderDetailsRepository.findById(id).orElse(null);
    }

    public OrderDetails createOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public OrderDetails updateOrderDetails(Long id, OrderDetails orderDetailsDetails) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).orElse(null);
        if (orderDetails != null) {
            orderDetails.setOrder(orderDetailsDetails.getOrder());
            orderDetails.setDish(orderDetailsDetails.getDish());
            orderDetails.setQuantity(orderDetailsDetails.getQuantity());
            orderDetails.setPrice(orderDetailsDetails.getPrice());
            return orderDetailsRepository.save(orderDetails);
        }
        return null;
    }

    public void deleteOrderDetails(Long id) {
        orderDetailsRepository.deleteById(id);
    }
}