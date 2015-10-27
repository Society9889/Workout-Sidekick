package com.usability.workoutsidekick;

import java.util.HashMap;
import java.util.List;

public class ExerciseCatalog {
	
	private HashMap<String, List<Exercise>> table = new HashMap<String, List<Exercise>>();

	public void add(String muscle, Exercise exercise) {
		List<Exercise> exerciseList = table.get(muscle);
		exerciseList.add(exercise);
	}
	
	public List<Exercise> get(String muscle) {
		return table.get(muscle);
		
	}

}