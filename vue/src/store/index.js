import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'


Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    search: "",
    typeAheadResults: [],
    ingredients: [],
    recipes: [],
    addedRecipes: [
      {
        recipeId: "",
        name: "",
      }
    ],
    ingredient: {
      quantity: Number(""),
      unit: "",
      name: "",
      id: ""
    },
    addedIngredients: [{
      quantity: Number(),
      unit: "",
      name: "",
      id: "",
    }],
    meal: {
      id: "",
      name: "",
      recipes: [],
    },
    addedMeals: [
    ],
    mealPlan: {
      name: "",
      startDate: "",
      endDate: "",
      mealList: [{
        id: "",
        type: "",
        day: Number(),
        recipes: [
          {
            id: Number(),
            name: ""
          }
        ],
      }],
      userId: "",
      mealPlans: []
    }



  },


  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    DELETE_RECIPE_MY_RECIPES(state, payload){
      state.recipes.splice(payload.index, 1)
    },
    ADD_RECIPE(state, payload) {
      state.addedRecipes.push(payload);
    },
    CREATE_RECIPE(state, recipe) {
      state.recipes.push(recipe);
    },
    REMOVE_RECIPE(state, payload) {
      state.recipes.splice(payload.index, 1);
    },
    UPDATE_SEARCH(state, payload) {
      state.search = payload;
    },
    CLEAR_RECIPES(state) {
      state.addedRecipes = [
        {
          quantity: Number(),
          unit: "",
          name: "",
        }

      ];
    },
    CLEAR_INGREDIENTS(state) {
      state.addedIngredients = [{
        quantity: Number(),
        unit: "",
        name: "",
        id: ""
      }]
    },
    ADD_INGREDIENT(state, payload) {
      state.addedIngredients.push(payload);
    },
    ADD_MEAL_RECIPE(state, payload) {
      // state.mealPlan.mealList.recipes.push(payload);
      // let theMeal = state.addedRecipes[payload];
      // if (theMeal) {
      //   theMeal.recipes.push({ id: 0, name: '' });
      // }
      payload.recipes.push({ id: 0, name: ''})
    },
    REMOVE_MEAL_RECIPE(state, payload) {
      state.meal.recipes.splice(payload.index, 1);
    },
    CREATE_INGREDIENT(state, ingredient) {
      state.ingredients.push(ingredient);
    },
    REMOVE_INGREDIENT(state, payload) {
      state.addedIngredients.splice(payload, 1);
    },

    REMOVE_MEAL(state, payload) {
      state.addedMeals.splice(payload, 1);
    },
    ADD_MEAL(state, payload) {
      payload.push({
        day: 0,
        id: 0,
        recipes: [
          {
            id: 0,
            name: ''
          }
        ],
        type: ''
      })
    },
    UPDATE_INGREDIENT(state, payload) {
      const updateIngredient = state.addedIngredients[payload.index];
      if (updateIngredient) {
        updateIngredient.name = payload.name;
        updateIngredient.id = payload.id;
      }
    },
    SET_INGREDIENTS_FROM_DB(state, ingredients) {
      state.ingredients = ingredients;
    },
    SET_RECIPES_FROM_DB(state, linda) {
      state.recipes = linda;
    },
    SET_MEALPLANS_FROM_DB(state, mealPlans) {
        state.mealPlans = mealPlans;
    }
  }
})
