package com.techelevator.dao;

import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealDao implements MealDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMealDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //add Meal object to meal table and return serial ID
    @Override
    public Meal createMeal (Meal meal) {
        String sql = "INSERT INTO meal VALUES (DEFAULT, ?, ?, ?) RETURNING meal_id";
        Integer newMealId = jdbcTemplate.queryForObject(sql, Integer.class, meal.getMealType(), meal.getMealDay(), meal.getMpId());
        meal.setId(newMealId);
        return meal;
    }

    //add recipe associations to a meal by inserting lines into meal_recipe table
    @Override
    public void createMealRecipes (Meal meal) {
        String sql = "INSERT INTO meal_recipe VALUES (?,?)";
        for (Recipe recipe : meal.getRecipeList()) {
            int curId = recipe.getRecipeId();
            jdbcTemplate.update(sql, meal.getId(), curId);
        }
    }

    @Override
    public List<Meal> getMealByMealPlan(MealPlan mealPlan) {
        List<Meal> result;
        String sql = "SELECT * FROM meal JOIN meal_plan ON meal.mp_id = meal_plan.mp_id " +
                "WHERE meal_plan.mp_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, mealPlan.getId());
        result = mapRowToMeal(sqlRowSet);
        return result;
    }


    private List<Meal> mapRowToMeal(SqlRowSet sqlRowSet) {
        List<Meal> result = new ArrayList<>();

        while(sqlRowSet.next()) {
            Meal curMeal = new Meal();
            curMeal.setMpId(sqlRowSet.getInt("mp_id"));
            curMeal.setId(sqlRowSet.getInt("meal_id"));
            curMeal.setMealType(sqlRowSet.getString("type"));
            curMeal.setMealDay(sqlRowSet.getInt("meal_day"));
            result.add(curMeal);
        }
        return result;
    }

}
