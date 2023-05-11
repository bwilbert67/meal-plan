<template>
  <div id="main" class="meal-form">
    <form class="meal-plan-form" v-on:submit.prevent="addNewMealPlan()">
      <h1 class="title">meal planner</h1>
      <div id="everything">
        <label>
          Title:
          <br />
          <input
            id="meal-name-input"
            class="boxDesign"
            placeholder="Name Your Meal Plan"
            v-model="mealPlan.name"
          />
        </label>

        <br />
        <input
          id="datebox"
          type="date"
          class="boxDesign"
          placeholder="Start Date"
          v-model="mealPlan.startDate"
          @change="updateDays"
        />

        <input
          id="datebox"
          type="date"
          class="boxDesign"
          placeholder="End Date"
          v-model="mealPlan.endDate"
          @change="updateDays"
        />
        <br />

        <div id="boogie" v-for="(day, dayIndex) in days" :key="dayIndex">
          <div class="day-heading">
            <span
              v-show="!day.mealPlan.expanded"
              @click="day.mealPlan.expanded = !day.mealPlan.expanded"
              ><i class="fa-solid fa-caret-right"></i
            ></span>
            <span
              v-show="day.mealPlan.expanded"
              @click="day.mealPlan.expanded = !day.mealPlan.expanded"
              ><i class="fa-solid fa-caret-down"></i
            ></span>
            Day {{ dayIndex + 1 }}
          </div>

          <!-- <br /> -->

          <div id="mealplan" v-show="day.mealPlan.expanded">
            <ul>
              <ul
                id="eachMeal"
                class="boxDesign"
                v-for="(meal, index1) in day.mealPlan.mealList"
                :key="index1"
              >
              <div class="testTest">
                <label id="type">Type:</label>
                <select
                  id="typedropdown"
                  placeholder="type"
                  class="boxDesign"
                  v-model="meal.mealType"
                >
                  <option value="">Choose type</option>
                  <option value="Breakfast">Breakfast</option>
                  <option value="Brunch">Brunch</option>
                  <option value="Lunch">Lunch</option>
                  <option value="Dinner">Dinner</option>
                  <option value="Snack">Snack</option>
                  <option value="Dessert">Dessert</option>
                </select>
              </div>

                <div class="testTest" v-for="(linda, index) in meal.recipeList" :key="index">
                  <label class="recipeRow" id="recipe">Recipes:</label>

                  <select
                    id="recipeOpts"
                    placeholder="recipes"
                    class="boxDesign recipeRow"
                    v-model="linda.recipeId"
                  >
                    <option
                      v-for="(recipe, index) in recipes"
                      :key="index"
                      :value="recipe.recipeId"
                    >
                      {{ recipe.recipeName }}
                    </option>
                  </select>

                  &nbsp;
                  <button
                    id="removeBtnRecipes"
                    class="boxDesign recipeRow"
                    type="button"
                    @click.prevent="removeRecipe(meal, index)"
                  >
                    Remove Recipe
                  </button>
                </div>

                <div id="btns">
                  <button
                    class="boxDesign"
                    id="addRecipeButton"
                    @click.prevent="addMealRecipe(meal)"
                  >
                    Add Recipe
                  </button>
                  <button
                    class="boxDesign"
                    @click.prevent="removeMeal(day.mealPlan.mealList, index1)"
                  >
                    Remove Meal
                  </button>
                </div>
              </ul>
            </ul>
            <button
            class="boxDesign"
            id="addButton"
            @click.prevent="addMeal(day.mealPlan.mealList, dayIndex)"
            v-show="day.mealPlan.expanded"
          >
            Add Meal
          </button>
          </div>
          <br />
        </div>
        
        <button id="saveMealPlan" class="boxDesign" type="button" @click="save">
          Save Meal Plan
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import mealPlanService from "../services/MealPlanService";
export default {
  name: "meal-plan-form",
  props: {
    mealPlanId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      dayCount: 1,
      mealPlan: {
        //dupe?
        id: 0,
        userId: this.$store.state.user.id,
        startDate: "",
        endDate: "",
        name: "",
        mealList: [
          {
            day: 0,
            id: 0,
            recipeList: [
              //recipeList
              {
                recipeId: 0,
                name: "",
              },
            ],
            mealType: "",
          },
        ],
      },
      selectedDay: null,
      days: [],
      meal: {
        //dupe?
        id: Number,
        recipes: [
          {
            id: Number(),
            name: "",
          },
        ],
      },
    };
  },
  methods: {
    updateDays() {
      this.days = [];
      for (let i = 0; i <= this.planLength; i++) {
        this.days.push({
          mealPlan: {
            id: 0,
            userId: this.$store.state.user.id,
            startDate: "",
            endDate: "",
            name: "",
            mealList: [
              {
                mealDay: i + 1,
                id: 0,
                recipeList: [
                  {
                    recipeId: 0,
                    name: "",
                  },
                ],
                mealType: "",
              },
            ],
            expanded: false,
          },
        });
      }
    },
    addRecipe() {
      this.$store.commit("ADD_RECIPE", {
        amount: Number(),
        unit: "",
        name: "",
      });
    },
    addMealRecipe(meal) {
      // this.$store.commit("ADD_MEAL_RECIPE", this.meal.recipeList.index);
      // this.$store.commit("ADD_MEAL_RECIPE", meal);
      meal.recipeList.push({ recipeId: 0, name: "" });
    },
    addMeal(mealList, dayIndex) {
      // this.$store.commit("ADD_MEAL", mealList);
      mealList.push({
        mealDay: dayIndex + 1,
        id: 0,
        recipeList: [
          {
            recipeId: 0,
            name: "",
          },
        ],
        mealType: "",
      });
    },
    removeMeal(mealList, index) {
      mealList.splice(index, 1);
    },
    removeRecipe(meal, index) {
      // let recipeInfo = {
      //   meal: meal,
      //   index: index,
      // };
      // this.$store.commit("REMOVE_MEAL_RECIPE", recipeInfo);
      meal.recipeList.splice(index, 1);
    },
    save() {
      let isRecipeFilled = true;
      this.days.forEach((curDay) =>
        curDay.mealPlan.mealList.forEach((curMeal) =>
            curMeal.recipeList.forEach((curRecipe) => {
              if (curRecipe.recipeId == 0) {
                isRecipeFilled = false;
              }
            })
          )
        );
      if (Number.isNaN(this.planLength) || this.mealPlan.name == "" ||!isRecipeFilled) {
        alert("Please Fill out Required Meal Plan Fields");
      } else {
        let data = this.days.map((day) => day.mealPlan);
        data = data.map((mp) => {
          mp.name = this.mealPlan.name;
          mp.startDate = this.mealPlan.startDate;
          mp.endDate = this.mealPlan.endDate;
          return mp;
        });
        mealPlanService
          .newFullMealPlan(data, this.$store.state.user.id)
          .then((resp) => {
            // mealPlanService.newFullMealPlan(this.days).then((resp) => {
            console.log(resp.status);
            this.$router.push({ name: "mymealplan" });
          });
      }
    },
  },
  computed: {
    mealPlanDays() {
      const start = new Date(this.mealPlan.startDate);
      const end = new Date(this.mealPlan.endDate);
      const days = [];
      for (let day = start; day <= end; day.setDate(day.getDate() + 1)) {
        days.push(new Date(day));
      }

      return days;
    },
    planLength() {
      let date1 = new Date(this.mealPlan.startDate);
      let date2 = new Date(this.mealPlan.endDate);
      let milli = date2.getTime() - date1.getTime();

      let TotalDays = Math.ceil(milli / (1000 * 3600 * 24));
      return TotalDays;
    },
    addedRecipes() {
      return this.$store.state.addedRecipes;
    },
    recipes() {
      return this.$store.state.recipes;
    },
  },

  created() {
    mealPlanService
      .getRecipesByUsername()
      .then((response) =>
        this.$store.commit("SET_RECIPES_FROM_DB", response.data)
      );
    this.$store.commit("CLEAR_RECIPES");
  },
};
</script>

<style scoped>

#main {
      background-image: url("../images/BackgroundFood.webp");
      background-repeat: no-repeat;
      background-size: 100% auto;
      min-height: 1000px;
    }

#boogie {
  display: flex;
  flex-direction: column;
}


.testTest {
  display: flex;
  align-items: center;
  max-height: 40px;
}

.recipeRow {
  display: flex;
  justify-content: center;
  flex-direction: row;
  margin: 0px !important;
}

#eachMeal {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#mealPlan{
  display: flex;
  justify-content: center;
  flex-direction: column;
}
#everything {
  background-color: rgba(154, 84, 168, 0.158);
  border-radius: 10px;
  border: 2px solid rgba(215, 98, 238, 0.308);
}

#meal-name-input {
  margin-bottom: 2%;
}
.meal-form {
  display: flex;
  justify-content: center;
}
.title {
  margin-top: 50px;
}
.boxDesign {
  border: 2px solid rgba(88, 172, 92, 0.815);
  border-radius: 10px;
  font-family: "Rubik", "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
  
}

#recipes {
  margin-top: 20px;
  display: block;
  width: 60%;
}

#recipe {
  margin-top: 20px;
  width: 500%;
  height: 50%;
  margin-bottom: 20px;
}

#type {
  width: 40%;
  height: 50%;
}

#typedropdown {
  display: block;
  width: 40%;
  height: 60%;
}
.meal-plan-form {
  padding-top: 50px;
}

.btns {
  display: flex;
  justify-content: right;
  margin-top: 80px;
  margin-left: 10px;
}
#datebox {
  height: 20px;
  width: 110px;
  margin-top: -23px;
  margin-left: 20px;
}

#addButton {
  justify-content: center;
  align-items: center;
  border: 2px solid #bfabcb;
  border-radius: 15px;
  background-color: white;
  color: green;
  width: 150px;
  height: 35px;
  margin-bottom: -100px;
}

ul{
padding: 10px;
margin: 0px;
}

#removeBtnRecipes {
  margin-bottom: 20px;
}

#saveMealPlan {
  margin-bottom: 2%;
}

</style>