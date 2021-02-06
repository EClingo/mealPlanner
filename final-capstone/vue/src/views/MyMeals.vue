<template>
  <div class="my-meals">
    <h1>My Meals</h1>
    <main-nav />
    <list-of-meals />
  </div>
</template>

<script>
import ListOfMeals from '../components/ListOfMeals.vue';
import MainNav from "../components/MainNav.vue";

export default {
  components: {
    MainNav,
    ListOfMeals,
  },
};
</script>

<style

</style>