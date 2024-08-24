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

import com.example.projbe.entity.DishIngredients;
import com.example.projbe.service.DishIngredientsService;

@RestController
@RequestMapping("/api/dish-ingredients")
public class DishIngredientsController {
    @Autowired
    private DishIngredientsService dishIngredientsService;

    @GetMapping
    public List<DishIngredients> getAllDishIngredients() {
        return dishIngredientsService.getAllDishIngredients();
    }

    @GetMapping("/{id}")
    public DishIngredients getDishIngredientById(@PathVariable Long id) {
        return dishIngredientsService.getDishIngredientById(id);
    }

    @PostMapping
    public DishIngredients createDishIngredient(@RequestBody DishIngredients dishIngredient) {
        return dishIngredientsService.createDishIngredient(dishIngredient);
    }

    @PutMapping("/{id}")
    public DishIngredients updateDishIngredient(@PathVariable Long id, @RequestBody DishIngredients dishIngredientDetails) {
        return dishIngredientsService.updateDishIngredient(id, dishIngredientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDishIngredient(@PathVariable Long id) {
        dishIngredientsService.deleteDishIngredient(id);
    }
}