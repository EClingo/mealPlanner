import axios from 'axios';

const http = axios.create( {
    baseURL: "http://localhost:8080"
});

export default {
    getMyMeals(userId){
        return http.get(`/meals/${userId}`);
    },

    addMeal(meal){
        return http.post('/addMeal', meal);
    }
}