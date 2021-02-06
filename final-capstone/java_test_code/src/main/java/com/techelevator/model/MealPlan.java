package com.techelevator.model;

public class MealPlan {
	
	private int mealPlanId;
	private String name;
	private int userId;
	
	public MealPlan(int mealPlanId, String name, int userId) {
		super();
		this.mealPlanId = mealPlanId;
		this.name = name;
		this.userId = userId;
	}
	
	public MealPlan() {}

	public int getMealPlanId() {
		return mealPlanId;
	}

	public void setMealPlanId(int mealPlanId) {
		this.mealPlanId = mealPlanId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
