<template>
<div id="main">
  <recipe-card/>
  
  <div>
    <button @click="$router.push({name:'addrecipe', params: {id:$route.params.id}})" v-if="$store.state.token != ''" >ADD RECIPE</button>
  </div>

</div>
</template>

<script>
import mealPlanService from '../services/MealPlanService'
import RecipeCard from '../components/RecipeCard.vue'
export default {
    components: {
        RecipeCard
    },
    created() {
      mealPlanService.getIngredients().then((response) => this.$store.commit("SET_INGREDIENTS_FROM_DB", response.data));
      mealPlanService.getRecipesByUsername().then((response) => this.$store.commit("SET_RECIPES_FROM_DB", response.data));
    }

}
</script>

<style scoped>

  #main {
      background-image: url("../images/BackgroundFood.webp");
      background-repeat: no-repeat;
      background-size: 100% auto;
      min-height: 1000px;
    }

  button{
    justify-content: center;
    align-items: center;
    border: 4px solid #BFABCB;
    border-radius: 15px;
    background-color: white;
    color:green;
    width: 150px;
    height: 35px;
    margin-bottom: 10px;
  }
</style>