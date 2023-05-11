<template>
  <div>
  
    <form id="edit-form" v-on:submit.prevent="editRecipe()">
      <h1>Edit Recipe</h1>
      <div>
        <label for="name">Name </label>
        <div>
          <br/>
          <input
            id="name"
            type="text"
            v-model="recipe.name"
            minlength="1"
            maxlength="500"
            size="75"
          />
        </div>
        
        <br />
        <label for="ingredients">Ingredients</label>
        <table>
        <tr
          class="ingredients-list-boxes"
          v-for="(input, k) in inputs"
          :key="k"
        >
          <!--Looping starts for ingredients:-->
          <input
            v-model="input.quantity"
            type="text"
            placeholder="quantity"
          />
          <input
            v-model="input.measurementType"
            type="text"
            placeholder="measurement type"
          />
          <input
            v-model="input.ingredientName"
            placeholder="ingredient"
            type="text"
            minlength="1"
            maxlength="500"
            size="50"
          />
          <span>
            
      <br />
      <button class="submit-btn" type="submit" value="Submit">
        Save Changes
      </button>
      <button
        class="submit-btn"
        v-on:click.prevent="cancelEditRecipe"
        type="cancel"
      >
        Cancel
      </button>
    </form>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService.js";
export default {
  data() {
    return {
      recipe: {
        recipeId: "",
        name: "",
        instructions: "",
        recipeIngredients: [],
      },
      inputs: [
        {
          quantity: "",
          measurementType: "",
          ingredientName: "",
          recipeId: this.$route.params.id
        },
      ],
    };
  },
  methods: {
    editRecipe() {
      const editedRecipe = {
        recipeId: this.recipe.recipeId,
        userId: this.$store.state.user.id,
        name: this.recipe.name,
        directions: this.recipe.directions,
        recipeIngredients: this.inputs,
        numberOfServings: this.recipe.numberOfServings,
      };
      console.log(editedRecipe);
      recipeService
        .updateRecipe(editedRecipe)
        .then((response) => {
          if (response.status === 200) {
            alert("Recipe updated!");
            this.$router.push("/myRecipes");
          }
        })
        .catch((err) => {
          console.error(err + " problem updating recipe.");
        });
    },
    add() {
      this.inputs.push({
        measurementQuantity: "",
        measurementType: "",
        ingredientName: "",
        recipeId: this.recipe.recipeId,
        recipeIngredientId: "",
      });
    },
    remove(index) {
      this.inputs.splice(index, 1);
    },
    cancelEditRecipe() {
      this.$router.push("/myRecipes");
    },
  },
  created() {
    recipeService.getRecipe(this.$route.params.id).then((response) => {
      this.recipe = response.data;
      this.inputs = this.recipe.recipeIngredients;
      console.log(this.recipe);
    });
  },
};
</script>

<style scoped>
#edit-form {
  background: #94c973;
}
</style>