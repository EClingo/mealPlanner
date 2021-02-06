package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;

public interface MealPlanDAO {
	
	List<MealPlan> viewMealPlans(int userId);
	
	MealPlan viewMealPlan(int planId);
	
	void createMealPlan(MealPlan mealPlan);
	
	void editMealPlan(MealPlan mealPlan);
		
	void deleteMealPlan(int planId);
	
}
