<template>
  <div>
    <div class="mealInfo" v-for="meal in allMyMeals" :key="meal.mealId">
      <div class="meal-one">
        <div class="row">Name:</div>
        {{ meal.name }}
        <!-- <div class="col">Recipe</div>
        <div class="col">Recipe</div>
        <div class="col">Recipe</div> -->
        <div class="addToMealPlan">
          <button>Add to Meal Plan</button>
        </div>
        <div class="edit">
          <button>Edit</button>
        </div>
        <div class="delete">
          <button>Delete</button>
        </div>
      </div>
      <br />
    </div>
    <br />
  </div>
</template>

<script>
import mealService from "../services/MealService";

export default {
  data() {
    return {
      allMyMeals: [],
      userId: this.$store.state.user.id,
    };
  },
  created() {
    mealService
      .getMyMeals(this.userId)
      .then((response) => {
        this.allMyMeals = response.data;
      })
      .catch((error) => {
        console.error(error + " cannot get my meals");
      });
  },
};
</script>

<style>
</style>