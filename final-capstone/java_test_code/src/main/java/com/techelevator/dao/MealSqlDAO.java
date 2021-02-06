package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Meal;
import com.techelevator.model.Schedule;

@Component
public class MealSqlDAO implements MealDAO {
	
	private JdbcTemplate template;
	private RecipeDAO dao;
	
	public MealSqlDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}
	

	@Override
	public Meal viewMeal(int mealId) {
		Meal meal = new Meal();
		String sql = "SELECT * FROM meals WHERE meal_id = ?";
		SqlRowSet results = template.queryForRowSet(sql, mealId);
		
		if (results.next()) {
			meal = mapRowToMeal(results);
		}
		return meal;
	}
	
	@Override
	public List<Meal> mealListByUser(int userId) {
		List<Meal> meals = new ArrayList<>();
		
		String sql = "SELECT * FROM meals WHERE user_id = ?";
		SqlRowSet results = template.queryForRowSet(sql, userId);
		
		while(results.next()) {
			Meal temp = mapRowToMeal(results);
			meals.add(temp);
		}
		
		return meals;
	}
	
	@Override
	public List<Meal> viewMealsInMealPlan(int mealPlanId) {
		List<Meal> mealsInPlan = new ArrayList<>();
		String sql = "SELECT * FROM meals JOIN schedules ON meals.meal_id = schedules.meal_id WHERE schedules.meal_plan_id = ?";
		
		SqlRowSet results = template.queryForRowSet(sql, mealPlanId);
		
		while(results.next()) {
			Meal temp = mapRowToMeal(results);
			mealsInPlan.add(temp);
		}
		
		return mealsInPlan;
	}

	@Override
	public void addMeal(Meal meal) {
		String sql = "INSERT INTO meals (name, user_id) VALUES (?, ?)";
		template.update(sql, meal.getName(), meal.getUserId());
		
	}

	@Override
	public void editMealName(Meal meal, int mealId) {
		String sql = "UPDATE meals SET name = ? WHERE user_id = ? AND meal_id = ?";
		template.update(sql, meal.getName(), meal.getUserId(), mealId);
	}

	@Override
	public void deleteMeal(int mealId) {
		String sqlMealsRecipes = "DELETE from meals_recipes WHERE meal_id = ?";
		template.update(sqlMealsRecipes, mealId);
		String sqlMealPlansMeals = "DELETE from schedules WHERE meal_id = ?";
		template.update(sqlMealPlansMeals, mealId);
		String sqlMeals = "DELETE from meals WHERE meal_id = ?";
		template.update(sqlMeals, mealId);
	}
	
	private Meal mapRowToMeal(SqlRowSet results) {
		Meal meal = new Meal();
		meal.setMealId(results.getInt("meal_id"));
		meal.setName(results.getString("name"));
		meal.setUserId(results.getInt("user_id"));
		
		return meal;
	}
}
	
