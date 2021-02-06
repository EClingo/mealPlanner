<template>
  <div class="create-meal-plan">
    <h1>Create A New Meal Plan</h1>
    <router-link :to="{ name: 'mealPlans' }">My Meal Plans</router-link>
    <add-new-meal-plan />
  </div>
</template>

<script>
import AddNewMealPlan from "../components/AddNewMealPlan";

export default {
  components: { AddNewMealPlan },
};
</script>

<style>


</style>