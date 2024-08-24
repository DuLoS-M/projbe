package com.example.projbe.service;

import com.example.projbe.entity.Ingredients;
import com.example.projbe.repository.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }

    public Ingredients getIngredientById(Long id) {
        return ingredientsRepository.findById(id).orElse(null);
    }

    public Ingredients createIngredient(Ingredients ingredient) {
        return ingredientsRepository.save(ingredient);
    }

    public Ingredients updateIngredient(Long id, Ingredients ingredientDetails) {
        Ingredients ingredient = ingredientsRepository.findById(id).orElse(null);
        if (ingredient != null) {
            ingredient.setName(ingredientDetails.getName());
            ingredient.setQuantity(ingredientDetails.getQuantity());
            ingredient.setUnit(ingredientDetails.getUnit());
            return ingredientsRepository.save(ingredient);
        }
        return null;
    }

    public void deleteIngredient(Long id) {
        ingredientsRepository.deleteById(id);
    }
}