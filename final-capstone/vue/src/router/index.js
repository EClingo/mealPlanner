import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import LandingPage from '../views/LandingPage.vue'
import MealPlans from '../views/MealPlans.vue'
import MyMeals from '../views/MyMeals.vue'
import MyRecipes from '../views/MyRecipes.vue'
import BrowseRecipes from '../views/BrowseRecipes.vue'
import GroceryList from '../views/GroceryList.vue'
import CreateNewMealPlan from '../views/CreateNewMealPlan.vue'
import CreateNewMeal from '../views/CreateNewMeal.vue'
import AddRecipe from '../components/AddRecipe.vue'
import RecipeDetails from '../components/RecipeDetails.vue'
import SearchRecipeDetails from '../components/SearchRecipeDetails.vue'

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
      path: '/home',
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
      path: "/",
      name: "landingPage",
      component: LandingPage,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/mealPlans",
      name: "mealPlans",
      component: MealPlans,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/mealPlans/createNewMeal",
      name: "createNewMeal",
      component: CreateNewMeal,
      meta: {
        requiresAuth: true
      }
    },
  {
    path: "/mealPlans/mewMealPlan",
    name: "newMealPlans",
    component: CreateNewMealPlan,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/myMeals",
    name: "myMeals",
    component: MyMeals,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/myRecipes",
    name: "myRecipes",
    component: MyRecipes,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/browseRecipes",
    name: "browseRecipes",
    component: BrowseRecipes,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/browseRecipes/:id",
    name: "searchRecipeDetails",
    component: SearchRecipeDetails,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/groceryList",
    name: "groceryList",
    component: GroceryList,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/addRecipe",
    name: "addRecipe",
    component: AddRecipe,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/recipeDetails/:id",
    name: "recipeDetails",
    component: RecipeDetails,
    meta: {
      requiresAuth: true
    }
  }
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
