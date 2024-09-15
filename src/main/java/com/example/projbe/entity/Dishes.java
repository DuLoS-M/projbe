package com.example.projbe.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "dishes")
public class Dishes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishIngredients> dishIngredients = new ArrayList<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DishIngredients> getDishIngredients() {
        return dishIngredients;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setDishIngredients(List<DishIngredients> dishIngredients) {
        this.dishIngredients.clear();
        if (dishIngredients != null) {
            this.dishIngredients.addAll(dishIngredients);
        }
    }

    // Helper methods to manage the relationship

    public void addDishIngredient(DishIngredients dishIngredient) {
        dishIngredients.add(dishIngredient);
        dishIngredient.setDish(this);
    }

    public void removeDishIngredient(DishIngredients dishIngredient) {
        dishIngredients.remove(dishIngredient);
        dishIngredient.setDish(null);
    }
}