package com.example.projbe.controller;

import com.example.projbe.entity.Dishes;
import com.example.projbe.entity.Ingredients;
import com.example.projbe.entity.News;
import com.example.projbe.repository.DishesRepository;
import com.example.projbe.repository.IngredientsRepository;
import com.example.projbe.service.FileStorageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.projbe.entity.DishIngredients;

@RestController
@RequestMapping("/api/dishes")
public class DishesController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @PostMapping
    public ResponseEntity<Dishes> createDish(@RequestParam("name") String name,
                                            @RequestParam("price") Double price,
                                            @RequestParam(value = "photoUrl", required = false) MultipartFile photoUrl,
                                            @RequestParam("description") String description,
                                            @RequestParam("ingredients") String ingredientsJson) {
        Dishes dish = new Dishes();
        dish.setName(name);
        dish.setPrice(price);
        dish.setDescription(description);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> ingredientsList;
        try {
            ingredientsList = objectMapper.readValue(ingredientsJson, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            return ResponseEntity.status(400).body(null);
        }

        // Convert each map to an Ingredients object and add to dish
        for (Map<String, Object> ingredientMap : ingredientsList) {
            int ingredientId = (Integer) ingredientMap.get("id");
            Ingredients ingredientEntity = ingredientsRepository.findById((long) ingredientId).orElse(null);
            Double quantity = (Double) ingredientMap.get("quantity");
            dish.addDishIngredient(ingredientEntity, quantity);
        }

        if (photoUrl != null && !photoUrl.isEmpty()) {
            try {
                String fileName = fileStorageService.storeFile(photoUrl);
                dish.setPhotoUrl("http://localhost:8080/uploads/" + fileName); // Ensure this path is correct
            } catch (IOException e) {
                return ResponseEntity.status(500).body(null);
            }
        }

        dishesRepository.save(dish);
        return ResponseEntity.ok(dish);
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