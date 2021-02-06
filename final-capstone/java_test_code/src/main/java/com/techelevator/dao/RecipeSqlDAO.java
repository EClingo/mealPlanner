package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;

@Component
public class RecipeSqlDAO implements RecipeDAO {
	
	private JdbcTemplate template;
	
	public RecipeSqlDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public List<Recipe> findAll(int id) {
		String listRecipesSql = "SELECT * FROM recipes WHERE user_id = ? ";
		SqlRowSet results = template.queryForRowSet(listRecipesSql, id);
		return mapResultsToRecipes(results);
	}

	@Override
	public Recipe getRecipeById(int id) {
		String recipeByIdSql = "SELECT * FROM recipes WHERE recipe_id = ?";
		SqlRowSet result = template.queryForRowSet(recipeByIdSql, id);
		if (result.next()) {
			return mapResultsToRecipe(result);
			
		} else {
			return null;
		}
	}

	@Override
	public Recipe getRecipeByName(String title) {
		//I think we should use a fuzzy String search here, see trigrams?
		String recipeByIdSql = "SELECT * FROM recipes WHERE title = ?";
		SqlRowSet result = template.queryForRowSet(recipeByIdSql, title);
		
		return mapResultsToRecipe(result);
	}

	@Override
	public void addNewRecipe(Recipe recipe) {
		String addRecipeSql = "INSERT INTO recipes (user_id, title, instructions) "+
	" VALUES (?, ?, ?)";
		template.update(addRecipeSql, recipe.getUserId(), recipe.getTitle(), recipe.getInstructions() );
	}

	@Override
	public void editRecipe(Recipe recipe) {
		String editRecipeSql = "UPDATE recipes SET user_id=?, title=?, instructions=? WHERE recipe_id = ?";
		template.update(editRecipeSql, recipe.getUserId(), recipe.getTitle(), recipe.getInstructions(), recipe.getRecipeId());
	}


	@Override
	public void deleteRecipe(int id) {
		String deleteRecipeSql = "DELETE FROM recipes WHERE recipe_id =?";
		template.update(deleteRecipeSql, id);

	}

	@Override
	public List<Ingredient> getRecipeIngredients(int Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Recipe> getMealRecipes(int mealId) {
		String sql = "SELECT * FROM recipes JOIN meals_recipes " + 
		"ON recipes.recipe_id = meals_recipes.recipe_id JOIN meals ON meals_recipes.meal_id = meals.meal_id WHERE meals.meal_id = ?";
		
		SqlRowSet results = template.queryForRowSet(sql, mealId);
			return mapResultsToRecipes(results);
			
		}
	
	@Override
	public void addRecipeToMeal(int recipeId, int mealId) {
		String sql = "INSERT INTO meals_recipes (meal_id, recipe_id) VALUES (?, ?)";
		
		template.update(sql, mealId, recipeId);
	}
	
	@Override 
	public void deleteRecipeFromMeal(int mealId, int recipeId) {
		String sql = "DELETE FROM meals_recipes WHERE meal_id = ? AND recipe_id = ?";
		template.update(sql, mealId, recipeId);
	}
	
	
	
	private Recipe mapResultsToRecipe(SqlRowSet result) {
		int id = result.getInt("recipe_id");
		int userId = result.getInt("user_id");
		String title = result.getString("title");
		String instructions = result.getString("instructions");
		
		Recipe recipe = new Recipe (id,  userId, title, instructions);
		return recipe;
	}
	private List<Recipe> mapResultsToRecipes(SqlRowSet results){
		List<Recipe> recipeList = new ArrayList<>();
		while (results.next()) {
			recipeList.add(mapResultsToRecipe(results));
		}
		return recipeList;
		}
	}
	


