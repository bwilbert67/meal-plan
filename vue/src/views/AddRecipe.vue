<template>
<div class="addRecipe" id="main">
    
  <recipe-form/>
 </div>
</template>

<script>
import RecipeForm from '../components/RecipeForm.vue'
export default {
  components: { RecipeForm },

}
</script>

<style scoped>

    #main {
      background-image: url("../images/BackgroundFood.webp");
      background-repeat: no-repeat;
      background-size: 100% auto;
    }
    .addRecipe{
        padding: 1rem;
        display: flex;
        flex-direction: column;
        align-items: center;
}
</style>