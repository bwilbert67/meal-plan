package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;

import java.util.List;

public interface MealDao {

    Meal createMeal(Meal meal);
    void createMealRecipes(Meal meal);
    List<Meal> getMealByMealPlan(MealPlan mealPlan);
}
