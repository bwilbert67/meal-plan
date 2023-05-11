package com.techelevator.controller;

import com.techelevator.exception.DeleteFailureException;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.service.VueService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
//^ Is this right?
//Add PreAuthorize if logged in users cannot do anything
public class VueController {

    VueService vueService;

    public VueController(VueService vueService) {
        this.vueService = vueService;
    }

    @GetMapping("users/recipes")
    public List<Recipe> getUserRecipes(Principal principal) {
        return vueService.getUserRecipes(principal.getName());
    }

    @GetMapping("users/recipes/{recipeId}")
    public Recipe getRecipeDetails(@PathVariable int recipeId) {
        return vueService.getRecipeDetails(recipeId);
    }

    @GetMapping("meal/{mealId}/recipes")
    public List<Recipe> getRecipesByMealId(@PathVariable int mealId) {
        return vueService.getRecipeByMealId(mealId);
    }

    @PostMapping("users/recipes")
    public Recipe createRecipe(Principal principal, @RequestBody Recipe recipe) {
        return vueService.createRecipe(principal.getName(), recipe);
    }

    @PutMapping("recipes/{recipeId}")
    public void addImageToRecipe(Principal principal, @RequestParam MultipartFile imgFile, @PathVariable Integer recipeId) {
        vueService.addImageToRecipe(imgFile, recipeId);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> getIngredients() {
        return vueService.getIngredients();
    }

    @PostMapping("/ingredients")
    public List<Ingredient> createIngredient(@RequestBody List<Ingredient> ingredient) {
        return vueService.createIngredients((ingredient));
    }

    @PostMapping("users/mealplan")
    public MealPlan createMealPlan(@RequestBody MealPlan mealPlan, Principal principal) {
        return vueService.createMealPlan(mealPlan, principal.getName());
    }

    @PostMapping("users/{userId}/mealplans")
    public List<MealPlan> createMealPlan(@RequestBody List<MealPlan> mealPlans, @PathVariable Integer userId, Principal principal) {

        List<MealPlan> savedMealPlans = new ArrayList<>();
        for (MealPlan mealPlan : mealPlans) {
            MealPlan savedPlan = vueService.createMealPlan(mealPlan, principal.getName());
            savedMealPlans.add(savedPlan);
        }

        return savedMealPlans;
    }

//    @GetMapping("users/mealplan")
//    public List<MealPlan> getUserMealPlans(Principal principal) {
//        return vueService.getUserMealPlans(principal.getName());
//    }

    @GetMapping("users/{userId}/mealplans")
    public List<MealPlan> getUserMealPlans(Principal principal) {
        return vueService.getUserMealPlans(principal.getName());
    }

    @DeleteMapping("recipes/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) throws DeleteFailureException {
        vueService.deleteRecipe(recipeId);
    }

    @DeleteMapping("users/mealPlan/{mpId}")
    public void deleteMealPlan(@PathVariable Integer mealPlanId) throws DeleteFailureException {
        vueService.deleteMealPlan(mealPlanId);
    }

//    @PutMapping("/recipes")
//    public Recipe updateRecipe(@RequestBody Recipe recipe) {
//        return vueService.updateRecipe(recipe);
//    }
}

