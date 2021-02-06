package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Ingredient;

@Component
public class IngredientSqlDAO implements IngredientDAO {
	
	private JdbcTemplate template;
	
	public IngredientSqlDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public void addNewIngredientToExistingRecipe(Ingredient ingredient, int recipeId) {
		String sql = "INSERT INTO ingredients (user_id, name, is_on_grocery_list, recipe_id, quantity, measurement) VALUES (?, ?, ?, ?, ?, ?)";
		 template.update(sql, ingredient.getUserId(), ingredient.getName(), ingredient.isOnGroceryList(), recipeId, ingredient.getQuantity(), ingredient.getMeasurement());
	}
	
	@Override
	public void addIngredientNotInRecipe(Ingredient ingredient) {
		String sql = "INSERT INTO ingredients (user_id, name, is_on_grocery_list) VALUES (?, ?, ?)";
		template.update(sql, ingredient.getUserId(), ingredient.getName(), ingredient.isOnGroceryList());
	}
	
	@Override
	public void addIngredientAndQuantityNotInRecipe(Ingredient ingredient) {
		String sql = "INSERT INTO ingredients (user_id, name, is_on_grocery_list, quantity) VALUES (?, ?, ?, ?)";
		template.update(sql, ingredient.getUserId(), ingredient.getName(), ingredient.isOnGroceryList(), ingredient.getQuantity());
	}
	
	@Override
	public void addExistingIngredientToGroceryList(int ingredientId) {
		String sql = "UPDATE ingredients SET is_on_grocery_list = ? WHERE ingredient_id = ?";
		template.update(sql, true, ingredientId);
	}

	@Override
	public void editIngredientForRecipe(Ingredient ingredient, int recipeId, int ingredientId) {
		String sql  = "UPDATE ingredients SET name = ?, is_on_grocery_list = ?, recipe_id = ?, quantity = ?, measurement = ? WHERE ingredient_id = ?";
		template.update(sql, ingredient.getName(), ingredient.isOnGroceryList(), recipeId, ingredient.getQuantity(), ingredient.getMeasurement(), ingredientId);

	}

	@Override
	public void deleteIngredient(int ingredientId) {
		String sql = "DELETE from ingredients WHERE ingredient_id = ?";
		template.update(sql, ingredientId);

	}
	
	@Override
	public List<Ingredient> viewIngredientsInRecipe(int recipeId) {
		List<Ingredient> ingredients = new ArrayList<>();
	
		String sql = "SELECT * FROM ingredients WHERE recipe_id = ?";
		
		SqlRowSet results = template.queryForRowSet(sql, recipeId);
		
		while(results.next()) {
			ingredients.add(mapResultsToIngredients(results));
		}
		
		return ingredients;
		
	}
	
	@Override
	public List<Ingredient> viewGroceryList(int userId) {
		List <Ingredient> groceries = new ArrayList<>();	
		String sql = "SELECT * FROM ingredients WHERE user_id=? AND is_on_grocery_list=true";
		SqlRowSet results = template.queryForRowSet(sql, userId);
		while (results.next()) {
			groceries.add(mapResultsToIngredients(results));
		}
		return groceries;
	}
	
	
	private Ingredient mapResultsToIngredients(SqlRowSet results){
		
		int ingredientId = results.getInt("ingredient_id");
		int userId = results.getInt("user_id");
		String name = results.getString("name");
		boolean isOnGroceryList = results.getBoolean("is_on_grocery_list");
		int recipeId = results.getInt("recipe_id");
		int quantity = results.getInt("quantity");
		String measurement = results.getString("measurement");

	Ingredient ingredient = new Ingredient(ingredientId, userId, name, isOnGroceryList, recipeId, quantity, measurement);
		
		return ingredient;
		

}

}
