<template>
  <div class="add-recipe">
    <h1>Add a Recipe</h1>
    <main-nav />&nbsp;&nbsp; <br />
    <br />
    <br />
    <br />
    <form class="form" v-on:submit.prevent="saveRecipe">
      <label for="recipe-name">Recipe Name:</label>
      <input class="recipe-name" v-model="recipe.title" id="recipe-name" />
      <br />
      <br />
      <label for="ingredients">Ingredients:</label>
      <textarea
        class="ingredients"
        id="ingredients"
        name="ingredients"
        rows="4"
        cols="50"
        v-model="recipe.ingredients"
      >
      </textarea>
      <br />
      <br />
      <label for="instructions">Instructions:</label>
      <textarea
        class="instructions"
        id="instructions"
        name="instructions"
        rows="4"
        cols="50"
        v-model="recipe.instructions"
      >
      </textarea>
      <br />
      <br />
      <button>Submit</button>
    </form>
  </div>
</template>

<script>
import RecipeService from "../services/RecipeService";
import MainNav from './MainNav.vue';

export default {
  components: { MainNav },
  data() {
    return {
      recipe: {
        title: "",
        category: "",
        instructions: "",
        ingredients: "",
        userId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    saveRecipe() {
      console.log("saving recipe");
      console.log(this.recipe.title);
      RecipeService.createRecipe(this.recipe).then((response) => {
        if (response.status === 201) {
          console.log("recipe created!");
          alert("Your recipe has been added!");
        }
      });
      this.recipe = {};
    },
  },
};
</script>

<style>

</style>