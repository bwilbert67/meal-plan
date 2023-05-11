package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class RecipeRowMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet resultSet, int i) throws SQLException {
        Recipe newRecipe = new Recipe();

        newRecipe.setRecipeId(resultSet.getInt("recipe_id"));
        newRecipe.setRecipeName(resultSet.getString("recipe_name"));
        newRecipe.setRecipeType(resultSet.getString("recipe_type"));
        newRecipe.setRecipeDescription(resultSet.getString("recipe_description"));

        byte[] imageData = resultSet.getBytes("img_data");
        String filename = resultSet.getString("img_file_name");


        if (imageData != null && filename != null) {
            // In order to use the binary data for the purposes we need (displaying it as an
            // image on a page) we will need to convert the data to a Base64-encoded String.
            // We can do this using the Base64 encoder provided by Java. We use the Base64
            // static getEncoder() method to get the actual encoder and then call the
            // encodeToString method with the byte array as the param. This will return a String
            // which we will be able to use
            String imageDataString = Base64.getEncoder().encodeToString(imageData);


            newRecipe.setImgFilename(filename);
            newRecipe.setImgData(imageDataString);
        }

        return newRecipe;
    }
}
