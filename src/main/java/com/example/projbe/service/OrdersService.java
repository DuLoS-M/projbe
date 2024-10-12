package com.example.projbe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.DTOs.OrderDTO;
import com.example.projbe.DTOs.OrderDetailDTO;
import com.example.projbe.entity.OrderDetails;
import com.example.projbe.entity.Orders;
import com.example.projbe.enums.OrderStatus;
import com.example.projbe.entity.Users;
import com.example.projbe.repository.OrdersRepository;
import com.example.projbe.repository.UsersRepository;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    public List<OrderDTO> getAllOrders() {
        return ordersRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        Orders order = ordersRepository.findById(id).orElse(null);
        return order != null ? convertToDTO(order) : null;
    }

    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return ordersRepository.findByUserId(userId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO createOrder(Orders order, Long userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user != null) {
            order.setUser(user);
            order.setStatus(OrderStatus.PENDING);
            // Set the order reference in each OrderDetails
            if (order.getOrderDetails() != null) {
                for (OrderDetails detail : order.getOrderDetails()) {
                    detail.setOrder(order);
                }
            }
            Orders savedOrder = ordersRepository.save(order);
            return convertToDTO(savedOrder);
        }
        return null;
    }

    public OrderDTO updateOrderStatus(Long id, OrderStatus status) {
        Orders order = ordersRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(status);
            Orders updatedOrder = ordersRepository.save(order);
            return convertToDTO(updatedOrder);
        }
        return null;
    }

    public OrderDTO updateOrder(Long id, Orders orderDetails) {
        Orders order = ordersRepository.findById(id).orElse(null);
        if (order != null) {
            order.setCustomerName(orderDetails.getCustomerName());
            order.setOrderDate(orderDetails.getOrderDate());
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setOrderDetails(orderDetails.getOrderDetails());
            order.setUser(orderDetails.getUser()); // Ensure the user is set
            // Set the order reference in each OrderDetails
            if (order.getOrderDetails() != null) {
                for (OrderDetails detail : order.getOrderDetails()) {
                    detail.setOrder(order);
                }
            }
            Orders updatedOrder = ordersRepository.save(order);
            return convertToDTO(updatedOrder);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        ordersRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Orders order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setStatus(order.getStatus().name());
        orderDTO.setOrderDetails(order.getOrderDetails().stream().map(this::convertToDetailDTO).collect(Collectors.toList()));
        return orderDTO;
    }

    private OrderDetailDTO convertToDetailDTO(OrderDetails orderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setDishId(orderDetail.getDish().getId());
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setPrice(orderDetail.getPrice());
        return orderDetailDTO;
    }
}