package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Schedule;
@Component
public class ScheduleSqlDAO implements ScheduleDAO {

	private JdbcTemplate template;
	
	public ScheduleSqlDAO (DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}
	
	@Override
	public void addNewSchedule(Schedule schedule) {
		String addNewScheduleSql = "INSERT INTO schedules (meal_plan_id, meal_id, day_of_the_week, time_of_day)"
				+ " VALUES (?, ?, ?, ?)";
		template.update(addNewScheduleSql, schedule.getMealPlanId(),schedule.getMealId(),
				schedule.getDayOfWeek(),schedule.getTimeOfDay());
		

	}

	@Override
	public void editSchedule(Schedule schedule) {
		String editScheduleSql = "UPDATE schedules SET meal_plan_id=?, meal_id=?, day_of_the_week=?, time_of_day=? WHERE schedule_id=?";
		template.update(editScheduleSql, schedule.getMealPlanId(),schedule.getMealId(),
				schedule.getDayOfWeek(),schedule.getTimeOfDay(), schedule.getScheduleId());

	}

	@Override
	public List<Schedule> viewScheduleForMealPlan(int mealPlanId) {
		String viewMealPlanScheduleSql = "SELECT * FROM schedules WHERE meal_plan_id=?";
		SqlRowSet results = template.queryForRowSet(viewMealPlanScheduleSql, mealPlanId);
		List<Schedule> schedule = new ArrayList<>();
		while (results.next()) {
			schedule.add(mapResultToSchedule(results));
			
		}
		
		return schedule;
	}

	@Override
	public void deleteMealFromSchedule(int scheduleId, int mealId) {
		String deleteMealFromScheduleSql = "DELETE * from schedules WHERE schedule_id=? AND WHERE meal_id=?";
		template.update(deleteMealFromScheduleSql, scheduleId, mealId);

	}
	
	private Schedule mapResultToSchedule (SqlRowSet result) {
		Schedule schedule = new Schedule();
		schedule.setScheduleId(result.getInt("schedule_id"));
		schedule.setMealPlanId(result.getInt("meal_plan_id"));
		schedule.setMealId(result.getInt("meal_id"));
		schedule.setDayOfWeek(result.getString("day_of_the_week"));
		schedule.setTimeOfDay(result.getString("time_of_day"));
		return schedule;
	}

}
