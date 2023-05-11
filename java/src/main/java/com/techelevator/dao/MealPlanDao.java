package com.techelevator.dao;

import com.techelevator.model.MealPlan;

import java.util.List;

public interface MealPlanDao {

    List<MealPlan> getUserMealPlans (Integer userId);
    MealPlan createMealPlan (Integer userId, MealPlan mealPlan);
    void deleteMealPlan (Integer mealPlanId);

}
