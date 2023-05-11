package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcRecipeIngredientDao implements RecipeIngredientDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeIngredientDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createRecipeIngredient(Integer recipeId, Ingredient ingredient, Integer measurementId) {
        String sql = "INSERT INTO recipe_ingredient VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, recipeId, ingredient.getId(), ingredient.getQuantity(), measurementId);
    }
}
