package com.example.projbe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projbe.entity.Dishes;
import com.example.projbe.repository.DishesRepository;

@Service
public class DishesService {

    @Autowired
    private DishesRepository dishesRepository;

    public Dishes createDish(Dishes dish) {
        return dishesRepository.save(dish);
    }

    public Dishes getDish(Long id) {
        Optional<Dishes> dish = dishesRepository.findById(id);
        return dish.orElse(null);
    }

    public Dishes updateDish(Long id, Dishes dishDetails) {
        Optional<Dishes> optionalDish = dishesRepository.findById(id);
        if (optionalDish.isPresent()) {
            Dishes dish = optionalDish.get();
            dish.setName(dishDetails.getName());
            dish.setPrice(dishDetails.getPrice());
            dish.setDescription(dishDetails.getDescription());
            dish.setDishIngredients(dishDetails.getDishIngredients());
            // dish.setOrderDetails(dishDetails.getOrderDetails());
            return dishesRepository.save(dish);
        } else {
            return null;
        }
    }

    public boolean deleteDish(Long id) {
        if (dishesRepository.existsById(id)) {
            dishesRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Dishes> getAllDishes() {
        return dishesRepository.findAll();
    }
}