package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Meal {
    private Integer id;
    private String name;
    private String mealType;
    private Integer mealDay;
    private List<Recipe> recipeList;
    private Integer mpId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String type) {
        this.mealType = type;
    }

    public Integer getMealDay() {
        return mealDay;
    }

    public void setMealDay(Integer mealDay) {
        this.mealDay = mealDay;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public Integer getMpId() {
        return mpId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }
}