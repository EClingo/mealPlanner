package com.techelevator.model;

public class Meal {
	
	private int mealId;
	private String name;
	private int userId;
	
	public Meal(int mealId, String name, int userId) {
		super();
		this.mealId = mealId;
		this.name = name;
		this.userId = userId;
	}
	
	public Meal() {}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
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
