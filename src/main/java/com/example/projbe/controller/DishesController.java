package com.example.projbe.controller;

import com.example.projbe.entity.Dishes;
import com.example.projbe.repository.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dishes")
public class DishesController {

    @Autowired
    private DishesRepository dishesRepository;

    @PostMapping
    public Dishes createDish(@RequestBody Dishes dish) {
        return dishesRepository.save(dish);
    }

    @GetMapping
    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dishes> getDishById(@PathVariable Long id) {
        Optional<Dishes> dish = dishesRepository.findById(id);
        if (dish.isPresent()) {
            return ResponseEntity.ok(dish.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dishes> updateDish(@PathVariable Long id, @RequestBody Dishes dishDetails) {
        Optional<Dishes> dish = dishesRepository.findById(id);
        if (dish.isPresent()) {
            Dishes updatedDish = dish.get();
            updatedDish.setName(dishDetails.getName());
            updatedDish.setPrice(dishDetails.getPrice());
            updatedDish.setDescription(dishDetails.getDescription());
            dishesRepository.save(updatedDish);
            return ResponseEntity.ok(updatedDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        Optional<Dishes> dish = dishesRepository.findById(id);
        if (dish.isPresent()) {
            dishesRepository.delete(dish.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}