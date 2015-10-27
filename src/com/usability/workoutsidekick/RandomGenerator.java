package com.usability.workoutsidekick;
import java.util.List;
import java.util.Random;

import android.content.Context;

public class RandomGenerator implements Generator {
	
	private Random randomGenerator = new Random();
	private Context context;
	
	
	public RandomGenerator(Context context){
		this.context = context;
	}
	
	/**
	 * Returns a random exercise with the given main muscle from the database. 
	 * Returns null if no exercises are found.
	 */
	@Override
	public Exercise generateExercise(String mainMuscle) {
		Exercise exercise;
		ExerciseDAO dao = new ExerciseDAO(context);
		
		//call database
		dao.open();
		//TODO - change to get by muscle
		List<Exercise> exercises = dao.getExercisesByMainMuscle(mainMuscle);
		dao.close();
		
		if (exercises.isEmpty())
			return null; //No exercises in database for this muscle
		
		//get random index
		int index = randomGenerator.nextInt(exercises.size());
		
		//return random exercise
		exercise = exercises.get(index);
		return exercise;
	}
	
}
