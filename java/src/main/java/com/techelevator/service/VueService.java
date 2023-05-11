package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.exception.DeleteFailureException;
import com.techelevator.exception.IncompleteRecipeException;
import com.techelevator.model.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class VueService {

    private final IngredientDao ingredientDao;
    private final RecipeDao recipeDao;
    private final RecipeIngredientDao recipeIngredientDao;
    private final JdbcMeasurementDao measurementDao;
    private final InstructionDao instructionDao;
    private final UserDao userDao;
    private final MealDao mealDao;
    private final MealPlanDao mealPlanDao;

    public VueService(IngredientDao ingredientDao, RecipeDao recipeDao, RecipeIngredientDao recipeIngredientDao, JdbcMeasurementDao measurementDao,
                      InstructionDao instructionDao, UserDao userDao, MealDao mealDao, MealPlanDao mealPlanDao) {
        this.ingredientDao = ingredientDao;
        this.recipeDao = recipeDao;
        this.recipeIngredientDao = recipeIngredientDao;
        this.measurementDao = measurementDao;
        this.instructionDao = instructionDao;
        this.userDao = userDao;
        this.mealDao = mealDao;
        this.mealPlanDao = mealPlanDao;
}

    public List<Recipe> getUserRecipes(String name) {
        List<Recipe> recipeList;
        recipeList = recipeDao.getUserRecipes(name);

        for(int i = 0; i < recipeList.size(); i++) {
            int curId = recipeList.get(i).getRecipeId();
            recipeList.set(i, getRecipeDetails(curId));
        }

        return recipeList;
    }

    //I don't think this annotation is working
    @Transactional
    public Recipe createRecipe(String name, Recipe recipe) throws IncompleteRecipeException{

        try {
            User curUser = userDao.findByUsername(name);
            Recipe recipeWithId = recipeDao.createRecipe(curUser.getId(), recipe);
            int id = recipeWithId.getRecipeId();
            recipe.setRecipeId(id);
            List<Ingredient> recipeIngredients = recipe.getIngredients();
            for (Ingredient ingredient : recipeIngredients) {
                int measurementId = measurementDao.getMeasurementIdByUnit(ingredient.getUnit());
                recipeIngredientDao.createRecipeIngredient(id, ingredient, measurementId);
            }
            for (Instruction instruction : recipe.getInstructions()) {
                instructionDao.addInstruction(id, instruction);
            }
            return recipe;
        } catch(Exception ex) {
            throw new IncompleteRecipeException();
        }
    }



    public List<Ingredient>createIngredients(List<Ingredient> ingredients) {
        return ingredientDao.createIngredients(ingredients);
    }

    public List<Ingredient> getIngredients() {

        List<Ingredient> result =ingredientDao.getIngredients();

        return result;
    }

    public Recipe getRecipeDetails(int recipeId) {
        Recipe result;

        result = recipeDao.getRecipeDetailsById(recipeId);

        List<Ingredient> ingredients = ingredientDao.getIngredientsByRecipeId(recipeId);
        List<Instruction> instructions = instructionDao.getInstructionsByRecipeId(recipeId);

        result.setIngredients(ingredients);
        result.setInstructions(instructions);


        return result;
    }

    public void addImageToRecipe(MultipartFile file, int recipeId) {
        recipeDao.addImageToRecipe(file, recipeId);
    }
    
    @Transactional
    public MealPlan createMealPlan(MealPlan mealPlan, String username) {
        User user = userDao.findByUsername(username);

        //change this to asssign method return to meal plan
        Integer mealPlanId = mealPlanDao.createMealPlan(user.getId(), mealPlan).getId();
        mealPlan.setId(mealPlanId);

        List<Meal> newMealList = new ArrayList<>();
        for(Meal curMeal : mealPlan.getMealList()) {
            curMeal.setMpId(mealPlanId);
            Meal mealWithId = mealDao.createMeal(curMeal);
            mealDao.createMealRecipes(mealWithId);
            newMealList.add(mealWithId);
        }
        mealPlan.setMealList(newMealList);

        return mealPlan;
    }

    public List<MealPlan> getUserMealPlans(String name) {
        User user = userDao.findByUsername(name);
        List<MealPlan> result = mealPlanDao.getUserMealPlans(user.getId());
        for(MealPlan curMealPlan : result) {
            curMealPlan.setMealList(mealDao.getMealByMealPlan(curMealPlan));
        }

        return result;
    }

    public List<Recipe> getRecipeByMealId(int mealId) {
        List<Recipe> result = recipeDao.getRecipesByMealId(mealId);
        for(Recipe curRecipe : result) {
            curRecipe.setInstructions(instructionDao.getInstructionsByRecipeId(curRecipe.getRecipeId()));
            curRecipe.setIngredients(ingredientDao.getIngredientsByRecipeId(curRecipe.getRecipeId()));
        }
        return result;
    }

    public void deleteRecipe(Integer recipeId) throws DeleteFailureException {
        try {
            recipeDao.deleteRecipe(recipeId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
    }

    public void deleteMealPlan(Integer mealPlanId) throws DeleteFailureException {
        try {
            mealPlanDao.deleteMealPlan(mealPlanId);
        } catch (Exception ex) {
            throw new DeleteFailureException(ex.getMessage());
        }
    }


//    public Recipe updateRecipe(Recipe recipe) {
//        return recipeIngredientDao.updateRecipe(recipe);
//    }

//    public Ingredient createIngredient(Ingredient ingredient) {
//        return ingredientDao.createIngredient(ingredient);
//    }
}
