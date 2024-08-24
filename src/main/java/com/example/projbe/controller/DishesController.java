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

import com.example.projbe.entity.Dishes;
import com.example.projbe.service.DishesService;

@RestController
@RequestMapping("/api/dishes")
public class DishesController {
    @Autowired
    private DishesService dishesService;

    @GetMapping
    public List<Dishes> getAllDishes() {
        return dishesService.getAllDishes();
    }

    @GetMapping("/{id}")
    public Dishes getDishById(@PathVariable Long id) {
        return dishesService.getDishById(id);
    }

    @PostMapping
    public Dishes createDish(@RequestBody Dishes dish) {
        return dishesService.createDish(dish);
    }

    @PutMapping("/{id}")
    public Dishes updateDish(@PathVariable Long id, @RequestBody Dishes dishDetails) {
        return dishesService.updateDish(id, dishDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishesService.deleteDish(id);
    }
}