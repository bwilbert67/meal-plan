package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;


public interface RecipeIngredientDao {
    void createRecipeIngredient(Integer recipeId, Ingredient ingredient, Integer measurementId);
}
