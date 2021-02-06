<template>
  <div class="browse-recipes">
    <h1>Browse Recipes</h1>
    
    <main-nav />
    <br />
    <search-recipes />
  </div>
</template>

<script>
import SearchRecipes from '../components/SearchRecipes.vue';
import MainNav from "../components/MainNav.vue";

export default {
  components: { MainNav, SearchRecipes },
};
</script>

<style>

</style>