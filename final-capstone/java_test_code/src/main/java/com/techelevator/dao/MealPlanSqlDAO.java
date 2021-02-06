package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Meal;
import com.techelevator.model.MealPlan;

@Component
public class MealPlanSqlDAO implements MealPlanDAO {

	private JdbcTemplate template;
	
	public MealPlanSqlDAO (DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}
	
	@Override
	public List<MealPlan> viewMealPlans(int userId) {
		String viewMealPlansSql = "SELECT * FROM meal_plans WHERE user_id = ?";
		
		SqlRowSet results = template.queryForRowSet(viewMealPlansSql, userId);
		return mapResultsToMealPlans(results);
	}
	

	@Override
	public MealPlan viewMealPlan(int mealPlanId) {
		String viewMealPlanSql = "SELECT * FROM meal_plans WHERE meal_plan_id =?";
		SqlRowSet result = template.queryForRowSet(viewMealPlanSql, mealPlanId);
		MealPlan plan = mapResultToMealPlan(result);
		return plan;
	}

	@Override
	public void createMealPlan(MealPlan mealPlan) {
		String createMealPlanSql = "INSERT INTO meal_plans (name, user_id)"
				+ "VALUES (?, ?)";
		template.update(createMealPlanSql, mealPlan.getName(), mealPlan.getUserId());

	}

	@Override
	public void editMealPlan(MealPlan mealPlan) {
		String editMealPlanSql = "UPDATE meal_plans SET name=? WHERE user_id = ?";
		template.update(editMealPlanSql, mealPlan.getName(), mealPlan.getUserId());
	}
	
	@Override
	public void deleteMealPlan(int planId) {
		String deletePlanSqlOnMealPlan = "DELETE from meal_plans WHERE meal_plan_id =? ";
		template.update(deletePlanSqlOnMealPlan, planId);
		
		String deletePlanOnSchedule = "DELETE from schedules WHERE meal_plan_id = ?";
		template.update(deletePlanOnSchedule, planId);
		
	}
	private MealPlan mapResultToMealPlan (SqlRowSet result) {
		int planId = result.getInt("meal_plan_id");
		String name = result.getString("name");
		int userId = result.getInt("user_id");
		MealPlan mealPlan = new MealPlan(planId, name, userId);
		return mealPlan;
	}
	private List<MealPlan> mapResultsToMealPlans (SqlRowSet results){
		List<MealPlan> plans = new ArrayList<>();
		while (results.next()) {
			plans.add(mapResultToMealPlan(results));
		}
		return plans;
	}


}
