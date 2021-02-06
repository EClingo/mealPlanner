package com.techelevator.model;

public class Ingredient {

	private int ingredientId;
	private int userId;
	private String name;
	private boolean isOnGroceryList;
	private int recipeId;
	private int quantity;
	private String measurement;
	
	public Ingredient(int ingredientId, int userId, String name, boolean isOnGroceryList, int recipeId, int quantity,
			String measurement) {
		super();
		this.ingredientId = ingredientId;
		this.userId = userId;
		this.name = name;
		this.isOnGroceryList = isOnGroceryList;
		this.recipeId = recipeId;
		this.quantity = quantity;
		this.measurement = measurement;
	}
	public Ingredient() {}
	
	public boolean isOnGroceryList() {
		return isOnGroceryList;
	}
	public void setOnGroceryList(boolean isOnGroceryList) {
		this.isOnGroceryList = isOnGroceryList;
	}
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	
	}
	
