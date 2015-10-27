package com.usability.workoutsidekick.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.R;
 
public class DescriptionActivity extends GlobalActivity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);
        
		long id = (Long) getIntent().getExtras().get("id");
		fillExerciseScreen(id);
    }
    
	/**
	 * Fill the screen with the information from the passed in exercise.
	 */
	private void fillExerciseScreen(long id){
		ExerciseDAO dao = new ExerciseDAO(this);
		dao.open();
		Exercise exercise = dao.getExercise(id);
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
	}
	
}