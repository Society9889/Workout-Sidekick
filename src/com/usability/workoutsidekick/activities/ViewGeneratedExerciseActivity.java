package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.Generator;
import com.usability.workoutsidekick.R;
import com.usability.workoutsidekick.RandomGenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class ViewGeneratedExerciseActivity extends GlobalActivity{

	private Exercise exercise;
	private Button favoriteButton;
	private PorterDuffColorFilter cf = new PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
	private ExerciseDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_generated_exercise_activity);
		favoriteButton = (Button) findViewById(R.id.favorite);    
		
		long id = (Long) getIntent().getExtras().get("id");
		fillExerciseScreen(id);
	}
	
	public void regenerate(View view){
		Generator generator = new RandomGenerator(this);
		Exercise tempExercise = generator.generateExercise(exercise.getMainMuscle());
		
		int count = 0;
		//Ensures that the new exercise isn't one that was just generated
		while(tempExercise.getId() == exercise.getId()){
			if (count>10)
				break;
			tempExercise = generator.generateExercise(exercise.getMainMuscle());
			count++;
		}
		
		exercise = tempExercise;
		this.fillExerciseScreen(tempExercise.getId());
		
	}
	
	public void favorite(View view){
		dao.open();
		
		if (exercise.getFavorite() == 0){
			exercise.setFavorite(1);
			favoriteButton.getBackground().setColorFilter(cf);
			favoriteButton.setText("Unfavorite");
		}else{
			exercise.setFavorite(0);
			favoriteButton.getBackground().setColorFilter(null);
			favoriteButton.setText("Favorite");
		}
		
		dao.updateExercise(exercise);
		dao.close();
	}
	
	public void goToHome(View view){
		Intent nextScreen = new Intent(getApplicationContext(), HomeActivity.class);
		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nextScreen);
	}
	
	/**
	 * Fill the screen with the information from the passed in exercise.
	 */
	private void fillExerciseScreen(long id){
		dao = new ExerciseDAO(this);
		dao.open();
		exercise = dao.getExercise(id);
		dao.close();
		
		TextView name = (TextView)findViewById(R.id.name);    
		TextView mainMuscle = (TextView)findViewById(R.id.mainMuscle);
		TextView otherMuscles = (TextView)findViewById(R.id.otherMuscles);
		TextView description = (TextView)findViewById(R.id.description);
		TextView equipment = (TextView)findViewById(R.id.equipment);
		
		name.setText(exercise.getName());
		mainMuscle.setText(exercise.getMainMuscle());
		otherMuscles.setText(exercise.getOtherMuscles());
		description.setText(exercise.getDescription());
		equipment.setText(exercise.getEquipment());
		
		description.setMovementMethod(new ScrollingMovementMethod());
		
		if (exercise.getFavorite()==1){
			favoriteButton.getBackground().setColorFilter(cf);
			favoriteButton.setText("Unfavorite");
		}else{
			favoriteButton.getBackground().setColorFilter(null);
			favoriteButton.setText("Favorite");
		}
	}
	
}
