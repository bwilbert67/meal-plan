package com.techelevator.dao;

import com.techelevator.exception.DeleteFailureException;
import com.techelevator.model.Recipe;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;
    private final RecipeRowMapper rowMapper = new RecipeRowMapper();

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> getUserRecipes(String username) {
        String sql = "SELECT recipe_id, recipe_name, recipe_type, recipe_description, img_file_name, img_data  " +
                "FROM recipe " +
                "JOIN users ON recipe.user_id = users.user_id " +
                "WHERE username = ?";

//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
//
//        return mapRowToRecipeList(results);
        return  jdbcTemplate.query(sql, new Object[] {username}, rowMapper);

    }

    @Override
    //is this suppose to be void since post does not return anything?
    public Recipe createRecipe(Integer userId, Recipe recipe) {
        String sql = "INSERT INTO recipe (recipe_name, recipe_type, recipe_description, user_id) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING recipe_id";

        Integer newRecipeId = jdbcTemplate.queryForObject(sql, Integer.class, recipe.getRecipeName(), recipe.getRecipeType(),
                recipe.getRecipeDescription(), userId);
        recipe.setRecipeId(newRecipeId);

//        jdbcTemplate.update(sql, recipe.getRecipeName(),recipe.getRecipeType(),recipe.getRecipeDescription(),userId);

        return recipe;
    }

    @Override
    public Recipe getRecipeDetailsById(Integer id) {
        String sql = "SELECT * FROM recipe WHERE recipe_id = ?";
//        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql, id);
//        Recipe result = mapRowToRecipe(rowset);
//        return result;
        try {
            return jdbcTemplate.queryForObject(sql, new Object[] { id }, rowMapper);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void addImageToRecipe(MultipartFile file, int recipeId) {
        try {
            SqlLobValue data = new SqlLobValue(file.getBytes());
            String sql = "UPDATE recipe SET img_file_name = ?, img_data = ? WHERE recipe_id = ?";
            Object[] objs = new Object[] { file.getOriginalFilename(), data, recipeId };
            int [] types = new int[]{ Types.VARCHAR,  Types.BLOB, Types.INTEGER };

            int i = jdbcTemplate.update(sql, objs, types);
            System.out.println("count: " + i);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipe> getRecipesByMealId(int mealId) {
        String sql = "SELECT * FROM recipe JOIN meal_recipe ON recipe.recipe_id = " +
                "meal_recipe.recipe_id WHERE meal_recipe.meal_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, mealId);
        return mapRowToRecipeList(rowSet);
    }

    @Override
    public void deleteRecipe (Integer recipeId) {
        String sql = "DELETE  FROM meal_recipe WHERE recipe_id = ?";
        try {
            jdbcTemplate.update(sql, recipeId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
        String sql1 = "DELETE  FROM recipe_ingredient WHERE recipe_id = ?";
        try {
            jdbcTemplate.update(sql1, recipeId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }String sql2 = "DELETE  FROM instruction WHERE recipe_id = ?";
        try {
            jdbcTemplate.update(sql2, recipeId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
        String sql3 = "DELETE  FROM recipe WHERE recipe_id = ?";
        try {
            jdbcTemplate.update(sql3, recipeId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
    }

    private List<Recipe> mapRowToRecipeList(SqlRowSet results) {
        List<Recipe> recipeList = new ArrayList<>();
        while (results.next()) {
            Recipe newRecipe = new Recipe();
            newRecipe.setRecipeId(results.getInt("recipe_id"));
            newRecipe.setRecipeName(results.getString("recipe_name"));
            newRecipe.setRecipeType(results.getString("recipe_type"));
            newRecipe.setRecipeDescription(results.getString("recipe_description"));
            recipeList.add(newRecipe);
        }
        return recipeList;
    }

    private Recipe mapRowToRecipe(SqlRowSet result) {
        Recipe newRecipe = new Recipe();
        if(result.next()) {
            newRecipe.setRecipeId(result.getInt("recipe_id"));
            newRecipe.setRecipeName(result.getString("recipe_name"));
            newRecipe.setRecipeType(result.getString("recipe_type"));
            newRecipe.setRecipeDescription(result.getString("recipe_description"));
        }
        return newRecipe;
    }
}
