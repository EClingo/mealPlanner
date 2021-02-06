<template>
  <div class="links">
    <v-toolbar>
    <v-row justify="space-around">
      <v-btn><router-link v-bind:to="{ name: 'home' }">Home</router-link></v-btn>
      
     <v-btn><router-link :to="{ name: 'mealPlans' }">My Meal Plans</router-link></v-btn>
    

      <v-btn><router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link></v-btn>
  
    
      <v-btn><router-link :to="{ name: 'myMeals' }">My Meals</router-link></v-btn>
    
    
    
      <v-btn><router-link :to="{ name: 'myRecipes' }">My Recipes</router-link></v-btn>
    
    
    
      <v-btn><router-link :to="{ name: 'browseRecipes' }">Browse Recipes</router-link></v-btn>
    
    
    
      <v-btn><router-link :to="{ name: 'addRecipe' }">Add Recipe</router-link></v-btn>
    
    
    
      <v-btn><router-link :to="{ name: 'groceryList' }">Grocery List</router-link></v-btn>
    </v-row>
    </v-toolbar>
  </div>
  

</template>

<script>
export default {};
</script>

<style>


</style>