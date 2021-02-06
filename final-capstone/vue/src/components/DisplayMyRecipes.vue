<template>
  <div>
    <div
      class="recipeInfo"
      v-for="recipe in allMyRecipes"
      :key="recipe.recipeId"
    >
      <div class="infoContainer">
        <h3 class="recipeName">{{ recipe.title }}</h3>
        <router-link
          :to="{ name: 'recipeDetails', params: { id: recipe.recipeId } }"
          >View Recipe Details</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import recipeService from "../services/RecipeService";

export default {
  data() {
    return {
      allMyRecipes: [],
      userId: this.$store.state.user.id,
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
};
</script>

<style>
.recipeInfo {
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
}

.infoContainer {
  grid-column: 2;
}
</style>