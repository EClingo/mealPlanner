import axios from 'axios';

//Patricks API KEY: 030a3f462e26447e8748ab26ae6ebd1f
//Annas API KEY: 7673afa2c5ea4fe48c062eacd523f588
//Patrics 2nd API KEY: ece0f10c1c3d44758586ffdca2fafa6e

const http = axios.create( {
    baseURL: "https://api.spoonacular.com/recipes"
});

export default {
    searchRecipes(searchTerm) {
        return http.get(`/complexSearch?query=${searchTerm}&addRecipeInformation=true&apiKey=7673afa2c5ea4fe48c062eacd523f588`);
    },

    getRandom(){
        return http.get('/random?apiKey=7673afa2c5ea4fe48c062eacd523f588');
    },

    getIngredients(id){
        return http.get(`/${id}/ingredientWidget.json?apiKey=7673afa2c5ea4fe48c062eacd523f588`);
    },

    // for recipe search details, gives us title, image and instructions
    getRecipeInfo(id){
        return http.get(`https://api.spoonacular.com/recipes/${id}/information?includeNutrition=false&apiKey=7673afa2c5ea4fe48c062eacd523f588`);
    }



}