<template>
  <div>
    <v-row justify="center">
    <form v-on:submit.prevent="searchRecipes()">
      <label class="search" for="recipeSearch">Search</label>
      <input
        class="search-box"
        type="text"
        name="recipeSearch"
        id="recipeSearch"
        v-model="searchTerm"
      />
      <button>Submit</button>
    </form>
    </v-row>
    <v-card>
      <v-card-title>
        <h2> Search For a Recipe</h2>
      </v-card-title>
      <v-card-text>
        <v-form class="px-3">
          <v-text-field label="Keyword Search Here" v-model="searchTerm"></v-text-field>
        </v-form>
        <v-btn class="success" @click="searchRecipes()">Submit</v-btn>
      </v-card-text>
    </v-card>

    <div class="output" v-for="recipe in recipes" v-bind:key="recipe.id">
      <h2>{{ recipe.title }}</h2>
      <img v-bind:src="recipe.image" />

      <!-- <div
        v-for="part in recipe.analyzedInstructions[0].steps"
        v-bind:key="part.number"
      >
        <div
          v-for="ingredient in part.ingredients"
          v-bind:key="ingredient.id"
        >
          <p>{{ ingredient.name }}</p>
        </div>
        <p>{{ part.step }}</p>
      </div> -->
      <br /> <br />
      <router-link :to="{ name: 'searchRecipeDetails', params: {id: recipe.id} }">View Recipe Details</router-link>
    </div>
  </div>
</template>

<script>
import apiService from "../services/ApiService";

export default {
    props: {
    recipeId: Number,
  },
  data() {
    return {
      recipes: [],
      searchTerm: "",
    };
  },
  created() {},
  methods: {
    searchRecipes() {
      apiService.searchRecipes(this.searchTerm).then((response) => {
        if (response.status === 200) {
          this.recipes = response.data.results;
          console.log(response.data.results);
        }
      });
    },
  },
};
</script>

<style>


</style>