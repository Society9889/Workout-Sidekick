package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
	}
	
	/**
	 * Go to the first step in generating an exercise
	 */
	public void goToGenerateExercise(View view){
		Intent nextScreen = new Intent(getApplicationContext(), GenerateExerciseActivity.class);
		startActivity(nextScreen);
	}
	
	/**
	 * Go to the weekly schedule page
	 */
	public void goToWeeklySchedule(View view){
		Intent nextScreen = new Intent(getApplicationContext(), WeeklyScheduleActivity.class);
		startActivity(nextScreen);
	}
	
	/**
	 * Go to the exercise catalog
	 */
	public void goToExerciseCatalog(View view){
		Intent nextScreen = new Intent(getApplicationContext(), TabLayoutActivity.class);
		startActivity(nextScreen);
	}

	/** Sorry Evan, but no
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.unicorn, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.UNICORN:
	    		Intent nextScreen = new Intent(getApplicationContext(), UnicornActivity.class);
	    		startActivity(nextScreen);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}*/
}
