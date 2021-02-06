import axios from 'axios';

const http = axios.create( {
    baseURL: "http://localhost:8080"
});

export default {
    createRecipe(recipe){
        return http.post('/addRecipe', recipe);
    },

    getMyRecipes(userId){
        return http.get(`/myRecipes/${userId}`);
    },
    editRecipe(recipe){
        return http.put('/editRecipe', recipe);
    },
    getOneRecipe(id){
        return http.get(`/recipe/${id}`);
    },
    saveIngredients(recipeId, ingredient){
        return http.post(`/recipe/${recipeId}/addIngredient`, ingredient);
    }
}