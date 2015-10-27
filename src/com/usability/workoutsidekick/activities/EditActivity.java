package com.usability.workoutsidekick.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.R;
 
public class EditActivity extends GlobalActivity{

	private Exercise exercise;
	private long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);
		
		id = (Long) getIntent().getExtras().get("id");
		fillExerciseScreen(id);
	}

	/**
	 * Fill the screen with the information from the passed in exercise.
	 */
	private void fillExerciseScreen(long id){
		ExerciseDAO dao = new ExerciseDAO(this);
		dao.open();
		exercise = dao.getExercise(id);
		dao.close();
		
		EditText name = (EditText)findViewById(R.id.editName);    
		Spinner mainMuscle = (Spinner)findViewById(R.id.editSpinner);
		EditText description = (EditText)findViewById(R.id.editDescription);
		EditText otherMuscles = (EditText)findViewById(R.id.editOtherMuscle);
		Spinner equipment = (Spinner)findViewById(R.id.editEquipmentSpinner);
		
		name.setText(exercise.getName());
		description.setText(exercise.getDescription());
		otherMuscles.setText(exercise.getOtherMuscles());
		
		int i=0;
		String[] muscles = getResources().getStringArray(R.array.Muscles);
		String[] equip =  getResources().getStringArray(R.array.Equipment);
		for (String muscle : muscles){
			if (muscle.equals(exercise.getMainMuscle())){
				mainMuscle.setSelection(i, false);
				break;
			}
			else{
				i++;
			}
		}
		i = 0;
		for (String equipy : equip){
			if (equipy.equals(exercise.getMainMuscle())){
				equipment.setSelection(i, false);
				break;
			}
			else{
				i++;
			}
		}
	}
	
	public void cancel(View view){
		this.onBackPressed();
	}
	
	public void saveExcercise(View view){
		EditText name = (EditText) findViewById(R.id.editName);
		EditText description = (EditText) findViewById(R.id.editDescription);
		EditText otherMuscles = (EditText) findViewById(R.id.editOtherMuscle);
		Spinner mainMuscleSpinner = (Spinner) findViewById(R.id.editSpinner);
		Spinner equipmentSpinner = (Spinner) findViewById(R.id.editEquipmentSpinner);
		
		String selectedMuscle = mainMuscleSpinner.getSelectedItem().toString();
		String title = name.getText().toString();
		String selectedEquipment = equipmentSpinner.getSelectedItem().toString();
		
	//	Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_LONG).show();
		if(title.matches(".*\\w.*") && !title.contains("\n")){
			ExerciseDAO dao = new ExerciseDAO(this);
			Exercise ex = new Exercise();
			ex.setId(id);
			ex.setName(title);
			ex.setDescription( description.getText().toString());
			ex.setEquipment(selectedEquipment);
			ex.setMainMuscle(selectedMuscle);
			ex.setOtherMuscles(otherMuscles.getText().toString());
			
			dao.open();
			
			dao.updateExercise(ex);
			dao.close();
			this.onBackPressed();
			
		}else{
			invalidInput();
		}
		
	}
	
	public void invalidInput(){
		new AlertDialog.Builder(this)
	    .setTitle("Invalid Name")
	    .setMessage("Please enter a valid name")
	    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setCancelable(false)
	     .show();
	}
	
}