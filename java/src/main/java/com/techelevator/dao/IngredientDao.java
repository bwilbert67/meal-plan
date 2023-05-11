package com.techelevator.dao;

import com.techelevator.model.Ingredient;

import java.util.List;

public interface IngredientDao {

    List<Ingredient>createIngredients(List<Ingredient> ingredients);

    Ingredient createIngredient(Ingredient ingredient);
        //throws CreationFailureException?

    List<Ingredient> getIngredients();
    //rename method to: getIngredientList

    List<Ingredient> getIngredientsByRecipeId(int id);

    }
