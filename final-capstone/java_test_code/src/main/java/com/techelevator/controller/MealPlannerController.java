package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.IngredientDAO;
import com.techelevator.dao.MealDAO;
import com.techelevator.dao.MealPlanDAO;
import com.techelevator.dao.RecipeDAO;
import com.techelevator.dao.ScheduleDAO;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.Schedule;

@RestController
@CrossOrigin
@PreAuthorize("permitAll")
public class MealPlannerController {

	
	@Autowired
	RecipeDAO recipeDAO;
	@Autowired
	IngredientDAO ingredientDAO;
	@Autowired
	MealDAO mealDAO;
	@Autowired
	MealPlanDAO mealPlanDAO;
	@Autowired
	ScheduleDAO scheduleDAO;
	

//Recipes

	@GetMapping (path= {"/myRecipes/{id}"})
	public List<Recipe> recipeList (@PathVariable int id){
		List<Recipe> myRecipes = recipeDAO.findAll(id);
		return myRecipes;
	}
	
	@GetMapping("/recipe/{id}")
	public Recipe returnRecipeById (@PathVariable int id) {
		Recipe recipe = recipeDAO.getRecipeById(id);
		return recipe;
		
} 
	@PostMapping("/addRecipe")
	@ResponseStatus(HttpStatus.CREATED)
	public void addRecipe (@RequestBody Recipe recipe) {
		System.out.println(recipe.getUserId());
		recipeDAO.addNewRecipe(recipe);
	}
	
	@PutMapping("/editRecipe")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void editRecipe(@RequestBody Recipe recipe) {
		recipeDAO.editRecipe(recipe);
	}
	
	@DeleteMapping("/removeRecipe/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeRecipe (@PathVariable int id) {
		recipeDAO.deleteRecipe(id);
	}
	
	@GetMapping("/meal/{mealId}/recipes")
	public List<Recipe> recipesInMeal(@PathVariable int mealId) {
		List<Recipe> mealRecipes = recipeDAO.getMealRecipes(mealId);
		return mealRecipes;
	}
	
	@PostMapping("/meal/{mealId}/addRecipe/{recipeId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void addRecipeToMeal(@PathVariable int recipeId, @PathVariable int mealId) {
		recipeDAO.addRecipeToMeal(recipeId, mealId);
	}
	
	@DeleteMapping("/meal/{mealId}/recipe/{recipeId}/delete")
	@ResponseStatus(HttpStatus.OK)
	public void deleteRecipeFromMeal(@PathVariable int mealId, @PathVariable int recipeId) {
		recipeDAO.deleteRecipeFromMeal(mealId, recipeId);
	}
	
//Ingredients
	
	@PostMapping("/recipe/{recipeId}/addIngredient")
	@ResponseStatus(HttpStatus.CREATED)
	public void addNewIngredientToExistingRecipe(@RequestBody Ingredient ingredient, @PathVariable int recipeId) {
		ingredientDAO.addNewIngredientToExistingRecipe(ingredient, recipeId);
	}
	
	@PostMapping("/addIngredient") 
	@ResponseStatus(HttpStatus.CREATED)
	public void addIngredientNotInRecipe(@RequestBody Ingredient ingredient) {	 	
		ingredientDAO.addIngredientNotInRecipe(ingredient);
	}
	
	@PostMapping("/addIngredientAndQuantity") 
	@ResponseStatus(HttpStatus.CREATED)
	public void addIngredientAndQuantityNotInRecipe(@RequestBody Ingredient ingredient) {	 	
		ingredientDAO.addIngredientAndQuantityNotInRecipe(ingredient);
	}
	
	@PostMapping("/{ingredientId}/addToGroceryList") 
	@ResponseStatus(HttpStatus.CREATED)
	public void addExistingIngredientToGroceryList(@PathVariable int ingredientId) {
		ingredientDAO.addExistingIngredientToGroceryList(ingredientId);
	}
	
	@PutMapping("/recipe/{recipeId}/editIngredient/{ingredientId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void editIngredient(@RequestBody Ingredient ingredient, @PathVariable int recipeId, @PathVariable int ingredientId) {
		ingredientDAO.editIngredientForRecipe(ingredient, recipeId, ingredientId);
	}
	
	@DeleteMapping("/deleteIngredient/{ingredientId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteIngredient(@PathVariable int ingredientId) {
		ingredientDAO.deleteIngredient(ingredientId);
	}
	
	@GetMapping("/recipe/{recipeId}/ingredients")
	public List<Ingredient> viewIngredientsInRecipe(@PathVariable int recipeId) {
		List<Ingredient> ingredients = ingredientDAO.viewIngredientsInRecipe(recipeId);
		return ingredients;
	}
	
	@GetMapping("/myGroceryList/{userId}")
	public List<Ingredient> viewGroceryList(@PathVariable int userId) {
		List<Ingredient> groceryList = ingredientDAO.viewGroceryList(userId);
		return groceryList;
	}

	
//Meal Plans
	@GetMapping("/mealPlans/{userId}")
	public List<MealPlan> listMealPlans( @PathVariable int userId){
		List<MealPlan> mealPlanList = mealPlanDAO.viewMealPlans(userId);
		return mealPlanList;
	}

	@GetMapping("/mealPlan/{planId}")
	public MealPlan viewMealPlan (@PathVariable int planId) {
		MealPlan plan = mealPlanDAO.viewMealPlan(planId);
		return plan;
	}
	
	@PostMapping("/addMealPlan")
	@ResponseStatus(HttpStatus.CREATED)
	public void addMealPlan(@RequestBody MealPlan mealPlan) {
		mealPlanDAO.createMealPlan(mealPlan);
	}
	
	@PutMapping("/editMealPlan")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void editMealPlan(@RequestBody MealPlan mealPlan) {
		mealPlanDAO.editMealPlan(mealPlan);
	}

	@DeleteMapping("/deleteMealPlan")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteMealPlan(@PathVariable int planId) {
		mealPlanDAO.deleteMealPlan(planId);
	}

//Meals
	
	@GetMapping("/meal/{mealId}/view")
	public Meal viewMeal(@PathVariable int mealId) {
		Meal meal = mealDAO.viewMeal(mealId);
		return meal;
	}
	
	@GetMapping("/meals/{userId}")
	public List<Meal> mealListByUser(@PathVariable int userId) {
		List<Meal> meals = mealDAO.mealListByUser(userId);
		return meals;
	}
	
	@GetMapping("/meals/mealPlan/{mealPlanId}")
	public List<Meal> viewMealsInMealPlan(@PathVariable int mealPlanId) {
		List<Meal> meals = mealDAO.viewMealsInMealPlan(mealPlanId);
		return meals;
	}
	
	@PostMapping("/addMeal")
	@ResponseStatus(HttpStatus.CREATED)
	public void addMeal(@RequestBody Meal meal) {
		mealDAO.addMeal(meal);
	}
	
	@PutMapping("/meal/{mealId}/edit")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void editMealName (@RequestBody Meal meal, @PathVariable int mealId) {
		mealDAO.editMealName(meal, mealId);
	}
	
	@DeleteMapping("/meal/{mealId}/delete")
	@ResponseStatus(HttpStatus.OK)
	public void deleteMeal(@PathVariable int mealId) {
		mealDAO.deleteMeal(mealId);
	}
	
	//schedule
	@GetMapping("/getSchedule")
		public List<Schedule> listSchedule(int mealPlanId){
			return scheduleDAO.viewScheduleForMealPlan(mealPlanId);
		}
	

	@PostMapping("/addSchedule")
	@ResponseStatus(HttpStatus.CREATED)
	public void addSchedule(Schedule schedule) {
		scheduleDAO.addNewSchedule(schedule);
	}
	@PutMapping("/editSchedule")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void editSchedule(Schedule schedule) {
		scheduleDAO.editSchedule(schedule);
	}
	
	@DeleteMapping("/removeMealFromSchedule")
	@ResponseStatus(HttpStatus.OK)
	public void removeMealFromScedule(int scheduleId, int mealId) {
		scheduleDAO.deleteMealFromSchedule(scheduleId, mealId);
	}
	
	}


