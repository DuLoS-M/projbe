package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}