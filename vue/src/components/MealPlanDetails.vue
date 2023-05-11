<template>
  <div>
    <h1 id="recipe-header">{{ mealPlanny.name }}</h1>
    
    <div class="items">
      <label>
        
        <ul>
        </ul>
      </label>


    </div>
    
    <!-- <div>
      <div>
        <router-link v-bind:to="{ name: 'groceries',
            params: { mealPlanId: mealPlan.mealPlanId }, }"
          tag="button"
          >Create Grocery List</router-link>
      </div>
    </div>
    <div>
      <router-link
        v-bind:to="{
          name: 'mymealplan',
          params: { mealPlanId: mealPlan.mealPlanId },
        }"
        tag="button"
        >Go Back to Meal Plans</router-link
      >
    </div>
      <div
        id="mealType"
        v-for="meal in dailyPlan.meals"
        v-bind:key="meal.mealId"
      >
        <router-link
        id="meal"
          v-bind:to="{
            name: 'mealDetails',
            params: { mealId: meal.mealId },
          }"
          >{{ meal.mealType }}</router-link
        >


        <div
          class="recipe-name"
          v-for="recipe in meal.recipes"
          v-bind:key="recipe.recipeId"
        >
          <router-link
            class="recipe-name-link"
            v-bind:to="{
              name: 'myRecipeDetails',
              params: { id: recipe.recipeId },
            }"
          >
            {{ recipe.name }}</router-link
          >
          |
          <button
            class="remove-recipe-btn"
            v-on:click.prevent="deleteRecipe(meal.mealId, recipe.recipeId)"
          >
            Remove
          </button>
        </div>
      </div> -->

  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService.js";
export default {
  data() {
    return {
      mealPlan: {},
      user: {
        userName: this.$store.state.user.username,
        id: this.$store.state.user.id,
      },
    };
  },
  methods: {
    deleteRecipe(mealId, recipeId) {
      console.log(mealId, recipeId);
      mealPlanService.deleteRecipe(mealId, recipeId).then(() => {
        this.$router.go();
      });
    },
  },
  computed:{
    mealPlanny(){
      return this.$store.state.mealPlans.find(mealPlan => mealPlan.id === this.$route.params.mealPlanId)
    }
  },
  created() {

    if (this.$route.params.mealPlanId) {
    mealPlanService
      .getMealPlanById(this.$route.params.mealPlanId)
      .then((response) => {
        this.mealPlan = response.data;
      });
    }
  },
};
</script>

<style scoped>

#mealType {
  background-color: rgba(255, 253, 253, 0.726);
}
</style>