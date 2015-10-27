package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.Generator;
import com.usability.workoutsidekick.R;
import com.usability.workoutsidekick.R.layout;
import com.usability.workoutsidekick.RandomGenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class GenerateExerciseActivity extends GlobalActivity{

	private Button generateButton;
	private String muscle_group;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.generate_exercise_activity);
		
		generateButton = (Button)findViewById(R.id.generateExercise);  
		generateButton.setEnabled(false);
		
		listViewItemOnClick();
	}
	
	public void generateExercise(View view){
		Generator generator = new RandomGenerator(this);
		Exercise exercise = generator.generateExercise(muscle_group);
		
		if (exercise == null){
			noExerciseFound(muscle_group);
		}else{
			Intent nextScreen = new Intent(getApplicationContext(), ViewGeneratedExerciseActivity.class);
			nextScreen.putExtra("id", exercise.getId());
			startActivity(nextScreen);
		}
		
	}
	
	public void noExerciseFound(String muscleGroup){
		new AlertDialog.Builder(this)
		.setTitle("No Exercises Found for " + muscleGroup)
	    .setMessage("Please choose a different muscle group and try again.")
	    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setCancelable(false)
	     .show();
	}
	
	public void listViewItemOnClick() {
		final ListView listview = (ListView) findViewById(R.id.listView1);
		final TextView selected = (TextView) findViewById(R.id.selectedMuscle);
		final ImageView muscle_man = (ImageView) findViewById(R.id.imageView1);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view,
		         int position, long id) {
		    	 String[] stuff =  getResources().getStringArray(R.array.Muscles);
		    	 String current_selection = stuff[(int) id];
		    	 muscle_group = current_selection;
		    	 selected.setText(current_selection);
		    	 int resID = getResources().getIdentifier(current_selection.toLowerCase(), "drawable",  getPackageName());
		    	 muscle_man.setImageResource(resID);
		    	 generateButton.setEnabled(true);
		      }

		    });

	}
	
	
}
