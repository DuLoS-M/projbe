package com.example.projbe.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.projbe.entity.Dishes;
import com.example.projbe.repository.DishesRepository;
import com.example.projbe.service.DishesService;

@RestController
@RequestMapping("/api/dishes")
public class DishesController {

    @Autowired
    private DishesService dishesService;

    @Autowired
    private DishesRepository dishesRepository;

    @PostMapping
    public ResponseEntity<Dishes> createDish(@RequestParam("name") String name,
                                             @RequestParam("price") Double price,
                                             @RequestParam(value = "photoUrl", required = false) MultipartFile photoUrl,
                                             @RequestParam("description") String description,
                                             @RequestParam("ingredients") String ingredientsJson) {
        try {
            Dishes dish = dishesService.createDish(name, price, description, photoUrl, ingredientsJson);
            return ResponseEntity.ok(dish);
        } catch (IOException e) {
            System.out.println(e);
            return ResponseEntity.status(500).body(null);
        }
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
            Dishes existingDish = dish.get();
            existingDish.setName(dishDetails.getName());
            existingDish.setPrice(dishDetails.getPrice());
            existingDish.setDescription(dishDetails.getDescription());
            existingDish.setPhotoUrl(dishDetails.getPhotoUrl());
            dishesRepository.save(existingDish);
            return ResponseEntity.ok(existingDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        Optional<Dishes> dish = dishesRepository.findById(id);
        if (dish.isPresent()) {
            dishesRepository.delete(dish.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}