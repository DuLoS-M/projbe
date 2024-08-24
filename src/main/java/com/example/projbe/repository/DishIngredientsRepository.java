package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.DishIngredients;

public interface DishIngredientsRepository extends JpaRepository<DishIngredients, Long> {
}