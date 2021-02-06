<template>
  <div class="meal-plans">
      <h1>My Meal Plans</h1>
      <main-nav />
      <router-link :to="{ name: 'createNewMeal' }">Create New Meal</router-link>&nbsp;&nbsp;
      <router-link :to="{ name: 'newMealPlans' }">Create New Meal Plans</router-link>&nbsp;&nbsp;
  </div>
</template>

<script>
import MainNav from '../components/MainNav.vue'

export default {
components: {
  MainNav
}
}
</script>

<style

</style>