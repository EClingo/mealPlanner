package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Schedule;

public interface ScheduleDAO {
	
	void addNewSchedule(Schedule schedule);
	
	void editSchedule(Schedule schedule);
	
	List<Schedule> viewScheduleForMealPlan(int mealPlanId);
	
	void deleteMealFromSchedule(int scheduleId, int mealId);
	
	

}
