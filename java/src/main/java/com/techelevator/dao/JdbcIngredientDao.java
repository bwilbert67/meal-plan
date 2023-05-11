package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcIngredientDao implements IngredientDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> createIngredients(List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size(); i++) {
            createIngredient(ingredients.get(i));
        }
        return  ingredients;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        String sql = "INSERT INTO ingredient VALUES (DEFAULT, ?) RETURNING ingredient_id";
        Integer ingredientId = jdbcTemplate.queryForObject(sql, Integer.class, ingredient.getName());
        ingredient.setId(ingredientId);
        return ingredient;
    }

    @Override
    //rename method to: getIngredientList
    public List<Ingredient> getIngredients() {
        String sql = "SELECT * FROM ingredient ORDER BY ingredient_name ASC";
        List<Ingredient> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while(rowSet.next()) {
            result.add(mapPartialIngredient(rowSet));
        }
        return result;
    }
    //getIngredientsByRecipeId return List<Ingredients> with amount unit contained in Ingredient

    @Override
    public List<Ingredient> getIngredientsByRecipeId(int id) {
        String sql = "SELECT ingredient_name, quantity, measurement_name, ingredient.ingredient_id\n" +
                "FROM ingredient\n" +
                "JOIN recipe_ingredient ON recipe_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                "JOIN recipe ON recipe.recipe_id = recipe_ingredient.recipe_id\n" +
                "JOIN measurement ON measurement.measurement_id = recipe_ingredient.measurement_id\n" +
                "WHERE recipe.recipe_id = ?";
        List<Ingredient> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        while(rowSet.next()) {
            Ingredient curIngredient = mapFullIngredients(rowSet);
            result.add(curIngredient);
        }
        return result;
    }

    private Ingredient mapFullIngredients(SqlRowSet rowset) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rowset.getInt("ingredient_id"));
        ingredient.setName(rowset.getString("ingredient_name"));
        ingredient.setQuantity(rowset.getDouble("quantity"));
        ingredient.setUnit(rowset.getString("measurement_name"));
        return ingredient;
    }

    private Ingredient mapPartialIngredient(SqlRowSet rowset) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rowset.getInt("ingredient_id"));
        ingredient.setName(rowset.getString("ingredient_name"));
        return ingredient;
    }

}



