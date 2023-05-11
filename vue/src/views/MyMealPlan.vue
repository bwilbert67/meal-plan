<template>
<div id= "main">
  <meal-plan/>
   <meal-card/>
</div>
</template>
name
<script>

import MealPlan from '../components/MealPlan.vue'
import MealCard from '../components/MealCard.vue'
import MealPlanService from '../services/MealPlanService.js'
export default {

    name: "MyMealPlans",
    components: {
        MealPlan,
        MealCard
    },
     created() {
        MealPlanService.getAllMealPlans(this.$store.state.user.id).then((response) => this.$store.commit("SET_MEALPLANS_FROM_DB", response.data))
    }

}
</script>

<style scoped>

    #main {
      background-image: url("../images/BackgroundFood.webp");
      background-repeat: no-repeat;
      background-size: 100% auto;
      min-height: 1000px;
      margin-top: -32px;

    }
</style>