package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}