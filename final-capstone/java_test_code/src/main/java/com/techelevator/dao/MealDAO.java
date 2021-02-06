package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Meal;
import com.techelevator.model.Recipe;

public interface MealDAO {
		
	Meal viewMeal(int mealId);
	
	List<Meal> mealListByUser(int userId);
	
	public List<Meal> viewMealsInMealPlan(int mealPlanId);
	
	void addMeal(Meal meal);
	
	void editMealName(Meal meal, int mealId);
	
	void deleteMeal(int mealId);
	
	
	
}
