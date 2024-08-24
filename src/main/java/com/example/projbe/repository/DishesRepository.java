package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.Dishes;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
}