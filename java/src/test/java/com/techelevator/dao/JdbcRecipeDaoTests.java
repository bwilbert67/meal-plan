package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcRecipeDaoTests extends BaseDaoTests{

    JdbcRecipeDao recipeDao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        recipeDao = new JdbcRecipeDao(jdbcTemplate);

        Recipe testRecipe1 = new Recipe();
        Recipe testRecipe2 = new Recipe();
        Recipe testRecipe3 = new Recipe();

    }

    @Test
    public void getUserRecipes_returnsExpectedValue() {

    }
}
