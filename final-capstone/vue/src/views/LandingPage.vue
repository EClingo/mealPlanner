<template>
  <div class="landing-page">
    <h1>Meal Planner</h1>
    <div class="landingButtons">
      <div class="buttons">
        <router-link :to="{ name: 'login' }">Log In</router-link>
      </div>
      <div class="buttons">
        <router-link :to="{ name: 'register' }">Register</router-link>
      </div>
    </div>
    <div
      class="image"
      v-if="image"
      style="width: auto; height: 50rem"
      :style="{ 'background-image': 'url(' + image + ')' }"
    ></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      image:
        "https://feast2thebeat.com/wp-content/uploads/2016/06/fttb-bg-food.jpg",
    };
  },
};
</script>

<style>

</style>