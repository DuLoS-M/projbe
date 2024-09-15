package com.example.projbe.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.projbe.entity.DishIngredients;
import com.example.projbe.entity.Dishes;
import com.example.projbe.entity.Ingredients;
import com.example.projbe.repository.DishIngredientsRepository;
import com.example.projbe.repository.DishesRepository;
import com.example.projbe.repository.IngredientsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DishesService {

    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private DishIngredientsRepository dishIngredientsRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Transactional
    public Dishes createDish(String name, Double price, String description, MultipartFile photoUrl, String ingredientsJson) throws IOException {
        System.out.println("\n\n\n");
        System.out.println("Creating dish: " + name);
        Dishes dish = new Dishes();
        dish.setName(name);
        dish.setPrice(price);
        dish.setDescription(description);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> ingredientsList = objectMapper.readValue(ingredientsJson, new TypeReference<List<Map<String, Object>>>() {});
        System.out.println("Ingredients: " + ingredientsList);

        for (Map<String, Object> ingredientMap : ingredientsList) {
            int ingredientId = (Integer) ingredientMap.get("id");
            Ingredients ingredientEntity = ingredientsRepository.findById((long) ingredientId).orElse(null);
            Number quantityNumber = (Number) ingredientMap.get("quantity");
            Double quantity = quantityNumber.doubleValue();
            DishIngredients dishIngredient = new DishIngredients();
            dishIngredient.setIngredient(ingredientEntity);
            dishIngredient.setDish(dish);
            dishIngredient.setQuantityRequired(quantity);
            dish.addDishIngredient(dishIngredient);
        }

        System.out.println("photoUrl: " + photoUrl);
        if (photoUrl != null && !photoUrl.isEmpty()) {
            System.out.println("Uploading photo");
            String fileName = fileStorageService.storeFile(photoUrl);
            dish.setPhotoUrl("http://localhost:8080/uploads/" + fileName);
        }

        dishesRepository.save(dish);
        System.out.println("\n\n\n");
        return dish;
    }
}