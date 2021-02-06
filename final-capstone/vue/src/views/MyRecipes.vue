<template>
  <div class="my-recipes">
      <h1>My Recipes</h1>
      <main-nav />
      <display-my-recipes />
  </div>
</template>

<script>
import DisplayMyRecipes from '../components/DisplayMyRecipes.vue';
import MainNav from '../components/MainNav.vue';

export default {
  components: {
    MainNav,
    DisplayMyRecipes
  }
}
</script>

<style>


</style>