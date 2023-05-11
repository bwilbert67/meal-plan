package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipeDao {

    List<Recipe> getUserRecipes (String username);
    Recipe createRecipe (Integer id, Recipe recipe);
    Recipe getRecipeDetailsById(Integer id);
    void addImageToRecipe(MultipartFile file, int recipeId);
    List<Recipe> getRecipesByMealId(int mealId);
    void deleteRecipe (Integer recipeId);

}
