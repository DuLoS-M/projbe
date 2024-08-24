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

import com.example.projbe.entity.Ingredients;
import com.example.projbe.service.IngredientsService;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {
    @Autowired
    private IngredientsService ingredientsService;

    @GetMapping
    public List<Ingredients> getAllIngredients() {
        return ingredientsService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Ingredients getIngredientById(@PathVariable Long id) {
        return ingredientsService.getIngredientById(id);
    }

    @PostMapping
    public Ingredients createIngredient(@RequestBody Ingredients ingredient) {
        return ingredientsService.createIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredients updateIngredient(@PathVariable Long id, @RequestBody Ingredients ingredientDetails) {
        return ingredientsService.updateIngredient(id, ingredientDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteIngredient(@PathVariable Long id) {
        ingredientsService.deleteIngredient(id);
    }
}