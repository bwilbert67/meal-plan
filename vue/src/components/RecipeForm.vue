<template>
  <form id="form" @submit.prevent="submitForm" ref="recipeForm">
    <label>
      Recipe Name:
      <br />
      <input
        class="boxDesign"
        type="text"
        placeholder="recipe title"
        v-model="recipe.recipeName"
        required
      />
    </label>
    <br />
    <div id="descriptionsLabel">
      <label>
        Description:
        <br />
        <textarea
          class="boxDesign"
          cols="30"
          rows="5"
          placeholder="enter decription"
          v-model="recipe.recipeDescription"
        ></textarea>
      </label>
    </div>
    <br>
 <label for="fileInput">Choose a file  <i class="fa fa-lg fa-camera"></i> <input type="file" ref="imageFile" id="fileInput" /></label>
    <br>

    <label>Type:</label>&nbsp;
    <select id="type" class="boxDesign" v-model="recipe.recipeType">
      <option value="snack">snack{{ recipe.recipeType > 1 ? "s" : "" }}</option>
      <option value="breakfast">
        breakfast{{ recipe.recipeType > 1 ? "s" : "" }}
      </option>
      <option value="lunch">lunch{{ recipe.recipeType > 1 ? "s" : "" }}</option>
      <option value="dinner">
        dinner{{ recipe.recipeType > 1 ? "s" : "" }}
      </option>
      <option value="dessert">
        dessert{{ recipe.recipeType > 1 ? "s" : "" }}
      </option>
    </select>
    <br />

    <label id="ingredient-label">
      Ingredients:
      <ul>
        <li v-for="(ingredient, index) in ingredients" :key="index">
          <input
            type="text"
            id="unitAmount"
            class="boxDesign"
            placeholder="qty"
            v-model="ingredient.quantity"
          />

          <select
            id="unit"
            placeholder="unit"
            v-model="ingredient.unit"
            class="boxDesign"
          >
            <option value="">Choose Unit</option>
            <option value="cup">
              cup{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="tablespoon">
              tablespoon{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="teaspoon">
              teaspoon{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="dash">
              dash{{ ingredient.quantity > 1 ? "es" : "" }}
            </option>
            <option value="bunch">
              bunch{{ ingredient.quantity > 1 ? "es" : "" }}
            </option>
            <option value="dash">
              dash{{ ingredient.quantity > 1 ? "es" : "" }}
            </option>
            <option value="small pinch">
              small pinch{{ ingredient.quantity > 1 ? "es" : "" }}
            </option>
            <option value="large pinch">
              large pinch{{ ingredient.quantity > 1 ? "es" : "" }}
            </option>
            <option value="pint">
              pint{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="quart">
              quart{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="oz">
              oz
            </option>
            <option value="lb">
              lb{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="mL">
              mL{{ ingredient.quantity > 1 ? "s" : "" }}
            </option>
            <option value="can">
              can (15 0z)
            </option>
            <option value="large can">
              can (28 oz)
            </option>
            <option value="none">
             
            </option>
          </select>

          <ingredient-type-ahead-search
            :ingredientIndex="index"
            placeholder="name"
            class="search"
          />

          <button
            id="removeBtnIngredients"
            class="boxDesign"
            type="button"
            @click="removeIngredient(index)"
          >
            Remove
          </button>
        </li>
      </ul>
      <button class="boxDesign" type="button" @click="addIngredient()">
        Add Ingredient
      </button>
    </label>

    <br />

    <div id="instructions">
      <label>
        Instructions:
        <ol>
          <li v-for="(instruct, index) in recipe.instructions" :key="index">
            <textarea
              id="instructionInput"
              class="boxDesign"
              v-model="instruct.details"
              required
            ></textarea
            >&nbsp;<button
              id="removeInstructionsBtn"
              class="boxDesign"
              type="button"
              @click="removeInstruction(index)"
            >
              Remove
            </button>
          </li>
        </ol>
        <button
          id="addInstruction"
          class="boxDesign"
          type="button"
          @click="addInstruction()"
        >
          Add Instruction
        </button>
      </label>
     
      <button class="boxDesign" type="submit" @click.prevent="submitForm">
        Save Recipe
      </button>
    </div>
  </form>
</template>

<script>
import mealPlanService from "../services/MealPlanService";
import IngredientTypeAheadSearch from "./IngredientTypeAheadSearch.vue";
export default {
  components: {
    IngredientTypeAheadSearch,
  },
  name: "recipe-form",
  props: {
    recipeId: {
      type: Number,
      default: 0,
    },
    stepNumber: {
      type: Number,
      default: 1,
    },
  },

  data() {
    return {
      stepCounter: 1,
      recipe: {
        recipeName: "",
        recipeDescription: "",
        recipeType: "",
        ingredients: [],
        instructions: [
          {
            id: Number(),
            details: "",
            stepNumber: 1,
            recipeId: "",
          },
        ],
      },
      filtered: {
        ingredients: {
          id: Number(),
          name: "",
        },
      },
    };
  },
  computed: {
    ingredients() {
      return this.$store.state.addedIngredients;
    },
  },
  methods: {
    addIngredient() {
      this.$store.commit("ADD_INGREDIENT", {
        quantity: Number(),
        unit: "",
        name: "",
        id: "",
      });
    },
    removeIngredient(index) {
      this.$store.commit("REMOVE_INGREDIENT", index);
    },
    addInstruction() {
      this.stepCounter = this.stepCounter + 1;
      this.recipe.instructions.push({
        stepNumber: this.stepCounter,
        details: "",
      });
    },
    removeInstruction(index) {
      this.recipe.instructions.splice(index, 1);
      this.stepCounter = this.stepCounter - 1;
    },
    submitForm() {
      if (this.recipeId === 0) {
          let updateIngredients = this.$store.state.addedIngredients.map(
          (ingredient) => {
            const found = this.$store.state.ingredients.find(curIng  => 
              curIng.name == ingredient.name);

              ingredient.id = found ? found.id : 0;
              return ingredient;
            });

        let ingredientsToSave = updateIngredients.filter((ing) => ing.id == 0);
        let alreadySavedIngredients = updateIngredients.filter((ing) => ing.id !== 0);

        mealPlanService.addIngredients(ingredientsToSave).then((resp) => {
          let finalIngredientList = alreadySavedIngredients.concat(resp.data)
          this.recipe.ingredients = finalIngredientList;
          mealPlanService.addRecipe(this.recipe).then((response) => {
            const fileInfo = this.$refs.imageFile.files[0];
            if (fileInfo) {
            mealPlanService
              .addImageToRecipe(fileInfo, response.data.recipeId)
              .then((response) => {
                this.$store.commit("CREATE_RECIPE", response.data);
                this.$router.push({ name: "myrecipes" });
              });
            } else {
              this.$store.commit("CREATE_RECIPE", response.data);
                this.$router.push({ name: "myrecipes" });
            }
          });
        });
      }
    }
  }
};
</script>

    
     

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Rubik:wght@300&display=swap");

#form {
  background-color: rgba(90, 177, 90, .8);
  border: 2px solid #aa4db1;
  border-radius: 10px;
  width: 45%;
  height: 60%;
  margin-left: 20px;
}
.search {
  margin-right: -115px;
}
ul {
  margin-top: -5px;
  list-style: none;
  margin-bottom: -5px;
}

.search {
  margin-left: -10px;
  width: 35%;
}
#removeBtn {
  border: 2px solid white;
  border-radius: 10px;
  font-family: "Rubik", "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  margin-left: 10px;
}

#placeholder {
  font-family: "Rubik", "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
  font-style: italic;
}

.boxDesign {
  border: 2px solid white;
  border-radius: 10px;
  font-family: "Rubik", "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
}

#fileInput{
  display: none;
}
#instructionInput {
  width: 80%;
}

#instructions {
  margin-top: 40px;
}

#descriptionsLabel {
  margin-top: 30px;
}

#unitAmount {
  width: 10%;
}

#addInstruction {
  margin-right: 10px;
  margin-bottom: 10px;
}

#type {
  margin-top: 10px;
  margin-bottom: 35px;
  width: 150px;
}
#removeBtnIngredients {
  margin-left: 2%;
}

label {
  color: white;
  -webkit-text-stroke: 0.2px black;
}

</style>
