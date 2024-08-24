package com.example.projbe.service;

import com.example.projbe.entity.DishIngredients;
import com.example.projbe.repository.DishIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishIngredientsService {
    @Autowired
    private DishIngredientsRepository dishIngredientsRepository;

    public List<DishIngredients> getAllDishIngredients() {
        return dishIngredientsRepository.findAll();
    }

    public DishIngredients getDishIngredientById(Long id) {
        return dishIngredientsRepository.findById(id).orElse(null);
    }

    public DishIngredients createDishIngredient(DishIngredients dishIngredient) {
        return dishIngredientsRepository.save(dishIngredient);
    }

    public DishIngredients updateDishIngredient(Long id, DishIngredients dishIngredientDetails) {
        DishIngredients dishIngredient = dishIngredientsRepository.findById(id).orElse(null);
        if (dishIngredient != null) {
            dishIngredient.setDish(dishIngredientDetails.getDish());
            dishIngredient.setIngredient(dishIngredientDetails.getIngredient());
            dishIngredient.setQuantityRequired(dishIngredientDetails.getQuantityRequired());
            return dishIngredientsRepository.save(dishIngredient);
        }
        return null;
    }

    public void deleteDishIngredient(Long id) {
        dishIngredientsRepository.deleteById(id);
    }
}