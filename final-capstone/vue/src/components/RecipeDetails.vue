<template>
  <div class="recipe-details">
    <h1>Recipe Details</h1>
    <main-nav />
    <h3 class="recipeName">{{ recipe.title }}</h3>
    <p class="ingredients"></p>
    <p class="instructions">{{ recipe.instructions }}</p>
    <button
      id="edit-btn"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
    >
      Edit
    </button>
    <form  v-if="showForm === true" >
      <div>
        <label for="name">Name: </label>
        <input type="text" placeholder="Enter new name" v-model="recipe.title" />
      </div>
      <div>
        <label for="instructions"> Instructions: </label>
        <textarea id="instructions" v-model="recipe.instructions" rows="8" cols="40"></textarea>
      </div>
      <input type="submit" value="Save" v-on:click="updateRecipe"/>
      <input type="button" value="Cancel" v-on:click.prevent="resetForm"/>
    </form>
  </div>
</template>

<script>
import RecipeService from "../services/RecipeService";
import MainNav from "./MainNav.vue";

export default {
  components: { MainNav },
  props: {
    recipeId: Number,
  },

  data() {
    return {
      recipe: {

      },
      showForm: false,
    };
  },
  created() {
    console.log("RECIPE ID" + " " + this.$route.params.id);
    RecipeService.getOneRecipe(this.$route.params.id).then((response) => {
      this.recipe = response.data;
    });
  },
  methods: {
    updateRecipe() {
      console.log("update recipe")
      const newRecipe = {
        userId: this.$store.state.user.id,
        recipeId: this.$route.params.id,
        title: this.recipe.title,
        instructions: this.recipe.instructions,
      };
        console.log(newRecipe.id)
      RecipeService.editRecipe(newRecipe)
        .then((response) => {
          if (response.status === 202) {
            alert("Recipe has been updated!");
            // this.$router.push("/recipeDetails/:id");
          }
        })
        .catch((error) => {
          console.error(error + " problem updating recipe.");
        });
    },
    resetForm(){
      this.showForm = false;
      this.newRecipe = {};
    }
  },
};
</script>

<style>
.recipe-details {
  text-align: center;
}
</style>