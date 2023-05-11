import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import MyRecipes from '../views/MyRecipes'
import RecipeSpecific from '../views/RecipeSpecific'
import AddRecipe from '../views/AddRecipe'
import MyMealPlan from '../views/MyMealPlan.vue'
import MealPlan from '../views/MealPlan.vue'
import Groceries from '../views/Groceries.vue'
import MealSpecific from '../views/MealSpecific.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/users/:id/recipes',
      name: 'myrecipes',
      component: MyRecipes,
      meta: {
        requiresAuth: true
      }
    },
    {
    path: '/users/:id/recipes/:recipeId',
    name: 'recipedetails',
    component: RecipeSpecific,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/users/:id/recipes/create',
    name: 'addrecipe',
    component: AddRecipe,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/users/:id/mymealplan",
    name: "mymealplan",
    component: MyMealPlan,
    meta: {
      requiresAuth: true,
    }
  },
    {
    path: "/users/:id/MealPlanForm",
    name: "MealPlan",
    component: MealPlan,
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: "/groceryList",
    name: "groceries",
    component: Groceries,
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: "/mealPlanDetails/:mealPlanId",
    name: "mealPlanDetails",
    component: MealSpecific,
    meta: {
      requiresAuth: true,
    }
  },
  {
    path: "/mealPlan/:mealPlanId",
    name: "mealPlan",
    component: MyMealPlan,
    meta: {
      requiresAuth: true,
    }
  },
]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
