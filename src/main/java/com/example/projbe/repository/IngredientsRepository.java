package com.example.projbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projbe.entity.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}