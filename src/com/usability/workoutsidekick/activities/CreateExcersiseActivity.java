package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateExcersiseActivity extends GlobalActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_activity);
		EditText name = (EditText) findViewById(R.id.editName);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.routine_menu, menu);
		return true;
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
	
	public void cancel(View view){
		this.onBackPressed();
	}
	
	public void saveExcercise(View view){
		EditText name = (EditText) findViewById(R.id.editName);
		EditText description = (EditText) findViewById(R.id.editDescription);
		EditText otherMuscles = (EditText) findViewById(R.id.otherMuscle);
		Spinner mainMuscleSpinner = (Spinner) findViewById(R.id.mainMuscleSpinner);
		Spinner equipmentSpinner = (Spinner) findViewById(R.id.equipmentSpinner);
		
		String selectedMuscle = mainMuscleSpinner.getSelectedItem().toString();
		String title = name.getText().toString();
		String selectedEquipment = equipmentSpinner.getSelectedItem().toString();
		
	//	Toast.makeText(getApplicationContext(), name.getText().toString(), Toast.LENGTH_LONG).show();
		if(title.matches(".*\\w.*") && !title.contains("\n")){
			ExerciseDAO dao = new ExerciseDAO(this);
			
			dao.open();
			dao.createExercise(title, selectedMuscle, description.getText().toString(), otherMuscles.getText().toString(), selectedEquipment);
			dao.close();
			this.onBackPressed();
			
		}else{
			invalidInput();
		}
	}

}
