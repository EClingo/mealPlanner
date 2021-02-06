package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;

public interface RecipeDAO {

	List<Recipe> findAll(int id);

	Recipe getRecipeById(int id);

	Recipe getRecipeByName (String title);
	
	void addNewRecipe(Recipe recipe);
	
	void editRecipe(Recipe recipe);
	
	void deleteRecipe(int id);
	
	List<Ingredient> getRecipeIngredients (int id);
	
	List<Recipe> getMealRecipes(int mealId);
	
	void addRecipeToMeal(int recipeId, int mealId);
	
	void deleteRecipeFromMeal(int mealId, int recipeId);
}