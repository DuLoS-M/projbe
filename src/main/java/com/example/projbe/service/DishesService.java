package com.example.projbe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.entity.Dishes;
import com.example.projbe.repository.DishesRepository;

@Service
public class DishesService {
    @Autowired
    private DishesRepository dishesRepository;

    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
    }

    public Dishes getDishById(Long id) {
        return dishesRepository.findById(id).orElse(null);
    }

    public Dishes createDish(Dishes dish) {
        return dishesRepository.save(dish);
    }

    public Dishes updateDish(Long id, Dishes dishDetails) {
        Dishes dish = dishesRepository.findById(id).orElse(null);
        if (dish != null) {
            dish.setName(dishDetails.getName());
            dish.setPrice(dishDetails.getPrice());
            dish.setDescription(dishDetails.getDescription());
            dish.setOrderDetails(dishDetails.getOrderDetails());
            dish.setDishIngredients(dishDetails.getDishIngredients());
            return dishesRepository.save(dish);
        }
        return null;
    }

    public void deleteDish(Long id) {
        dishesRepository.deleteById(id);
    }
}