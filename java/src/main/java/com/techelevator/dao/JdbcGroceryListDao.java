package com.techelevator.dao;

import com.techelevator.model.GroceryList;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public class JdbcGroceryListDao implements GroceryListDao{

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<GroceryList> getUserGroceryList(String username) {
        return null;
    }

    @Override
    public GroceryList createGroceryList(String username, GroceryList groceries) {
        return null;
    }

//    public GroceryList mapRowToGroceryList (SqlRowSet rowSet) {
//        GroceryList groceryList = new GroceryList();
//        groceryList.setGroceryListName(rowSet.)
//        return groceryList;
//    }

    //COULD BE HELPFUL TO MAKE GROCERY LIST FROM USER MEAL PLAN :)
//    SELECT meal_plan.mp_id, mp_name, mp_start, mp_end, meal_plan.user_id, meal.type, meal.meal_day,
//    recipe.recipe_name, recipe.recipe_description, ingredient_name
//    FROM meal_plan
//    JOIN meal ON meal.mp_id = meal_plan.mp_id
//    JOIN meal_recipe ON meal.meal_id = meal_recipe.meal_id
//    JOIN recipe ON meal_recipe.recipe_id = recipe.recipe_id
//    JOIN recipe_ingredient ON recipe.recipe_id = recipe_ingredient.recipe_id
//    JOIN ingredient ON recipe_ingredient.ingredient_id = ingredient.ingredient_id
//    JOIN instruction ON instruction.recipe_id = recipe.recipe_id
//    WHERE meal_plan.user_id = 3
//    GROUP BY meal_plan.mp_id, meal.type, meal.meal_day, recipe.recipe_name, recipe.recipe_description, ingredient_name
//    ORDER BY meal_plan.mp_id, meal_day, CASE type WHEN 'Breakfast' THEN 1 WHEN 'Lunch' THEN 2 WHEN 'Dinner'
//    THEN 3 ELSE 4 END;


}

