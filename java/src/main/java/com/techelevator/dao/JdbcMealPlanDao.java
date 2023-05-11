package com.techelevator.dao;

import com.techelevator.exception.DeleteFailureException;
import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMealPlanDao implements MealPlanDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MealPlan> getUserMealPlans(Integer userId) {
        String sql = "SELECT meal_plan.mp_id, mp_name, mp_start, mp_end, meal_plan.user_id\n" +
                "FROM meal_plan \n" +
                "WHERE meal_plan.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        return mapRowToMealPlanList(results);
    }

    @Override
    public MealPlan createMealPlan(Integer userId, MealPlan mealPlan) {
        String sql = "INSERT INTO meal_plan (mp_id, mp_name, mp_start, mp_end, user_id) " +
                "VALUES (DEFAULT, ?, ?, ?, ?) RETURNING mp_id";
        Integer newPlanId = jdbcTemplate.queryForObject(sql, Integer.class, mealPlan.getName(),
                mealPlan.getStartDate(), mealPlan.getEndDate(), userId);
        mealPlan.setId(newPlanId);
        return mealPlan;
    }

    @Override
    public void deleteMealPlan (Integer mealPlanId) {
        String sql = "DELETE * FROM meal_plan WHERE mp_id = ?";
        try {
            jdbcTemplate.update(sql, mealPlanId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
        String sql2 = "DELETE * FROM meal WHERE mp_id = ?";
        try {
            jdbcTemplate.update(sql2, mealPlanId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
    }

    private List<MealPlan> mapRowToMealPlanList (SqlRowSet results) {
        List<MealPlan> mealPlanList = new ArrayList<>();
        while (results.next()) {
            MealPlan newMealPlan = new MealPlan();
            newMealPlan.setId(results.getInt("mp_id"));
            newMealPlan.setName(results.getString("mp_name"));
            newMealPlan.setStartDate(results.getDate("mp_start"));
            newMealPlan.setEndDate(results.getDate("mp_end"));
            newMealPlan.setUserId(results.getInt("user_id"));
            mealPlanList.add(newMealPlan);
        }
        return mealPlanList;
    }
}

