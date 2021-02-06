<template>
  <div class="newMeal">
    <h1>Create New Meal</h1>
    <main-nav /><br />
    <br />
    <form class="meal-form">
      <label class="meal-form-element prompt">Name: </label><br />
      <input class="meal-name meal-form-element" id="meal-name" v-model="mealToSave.name"/>
      <p class="meal-form-element prompt">Check the recipes you'd like to add to this meal: </p>
      <div v-for="recipe in allMyRecipes" :key="recipe.recipeId" class="meal-checkbox meal-form-element">
        <input
          v-model="mealToSave.checkedItems"
          type="checkbox"
          v-bind:value="recipe.recipeId"
        />
        <label> {{ recipe.title }} </label>
      </div>
      <button v-on:click.prevent="saveMeal" class="meal-form-element add-meal-button">Add Meal</button>
    </form>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService";
import mealService from '../services/MealService';
import MainNav from './MainNav.vue';

export default {
  components: {
    MainNav
  },
  data() {
    return {
      allMyRecipes: [],
      mealToSave: {
        name: "",
        checkedItems:[],
        userId: this.$store.state.user.id
      },
      
      userId: this.$store.state.user.id
    };
  },
  created() {
    recipeService
      .getMyRecipes(this.userId)
      .then((response) => {
        this.allMyRecipes = response.data;
      })
      .catch((error) => {
        console.error(error + " cannot get my recipes");
      });
  },
  methods: {
    saveMeal(){
      console.log("Step One");
      console.log(this.mealToSave.checkedItems);
      this.mealToSave.userId = this.$store.state.user.id;
      mealService.addMeal(this.mealToSave).then((response) => {
        if(response.status === 201){
          alert("Your meal has been created!");
        }
      })
      .catch((error) => {
        console.error(error + " meal was not saved!");
      })
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Raleway&display=swap');

.newMeal {
  text-align: center;
}

.meal-form{
  font-family: Raleway, Verdana, Geneva, Tahoma, sans-serif;
  border: ridge black 1.5px;
  padding: 25px;
  width: 500px;
  margin-right: auto;
  margin-left: auto;
}

.prompt{
  font-weight: bolder;
}

.meal-form-element{
  margin: 10px;
  font-size: 2.5vh;
}

.add-meal-button {
  width: 180px;
  height: 40px;
  font-size: 2.5vh;
  background: #e7e5e5;
  margin-bottom: 25px;
  border-radius: 32px;
  text-align: center;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: all 0.1s ease-in-out;
  box-shadow:  6px 6px 10px rgba(0, 0, 0, 0.2);
  font-family: Raleway;
}

.add-meal-button:hover{
  background-color: #ffffff;
  box-shadow: 6px 6px 10px rgba(0, 0, 0, 0.2);
}

@media (max-width: 400px) {
  .meal-one,
  .meal-two,
  .meal-three {
    display: block;
  }
}
</style>