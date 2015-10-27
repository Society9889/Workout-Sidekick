package com.usability.workoutsidekick.activities;

import java.util.List;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.R;
import com.usability.workoutsidekick.RandomGenerator;
import com.usability.workoutsidekick.ScheduleDAO;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Display all exercises associated with a given day. Exercises are grouped by muscle group.
 * 
 * @author Jake
 */
public class RoutineActivity extends GlobalActivity{

	private String day;
	private ScheduleDAO scheduleDAO;
	private List<Exercise> exercises;
	private ArrayAdapter<Exercise> adapter;
	private RandomGenerator generator;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routine_activity);
        
        scheduleDAO = new ScheduleDAO(this);
        generator = new RandomGenerator(this);
        
        day = (String) getIntent().getExtras().get("day");
        fillScreen(day);
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    fillScreen(day);
	}
    
    /**
     * Fill screen with exercises from the given dayOfWeek.
     */
    private void fillScreen(String dayOfWeek){
    	TextView dayLabel = (TextView) findViewById(R.id.day_label);
    	dayLabel.setText(dayOfWeek);
    	
    	ListView exerciseListView = (ListView) findViewById(R.id.exercises_for_day);
    	
		scheduleDAO.open();
		exercises = scheduleDAO.getExercisesByDay(dayOfWeek);
		scheduleDAO.close();
    
        adapter = new ArrayAdapter<Exercise>(this, R.layout.single_exercise, exercises);
        exerciseListView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		listViewItemOnClick(exerciseListView);
    }
    
	public void listViewItemOnClick(ListView listview) {
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Intent nextScreen = new Intent(getApplicationContext(), DescriptionActivity.class);
				nextScreen.putExtra("id", exercises.get((int)id).getId());
				startActivity(nextScreen);
			}
		});
	
		/**
		 * Set on long click listener that allows multi selection from list view.
		 */
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
	    listview.setMultiChoiceModeListener(new MultiChoiceModeListener() {
	    	
	    	/**
	    	 * Set exercises in the list selected or unselected.
	    	 */
    	    @Override
    	    public void onItemCheckedStateChanged(ActionMode mode, int position,
    	                                          long id, boolean checked) {
    	        // Here you can do something when items are selected/de-selected,
    	    	if (checked){
    	    		exercises.get((int) id).setSelected(true);
    	    	}else{
    	    		exercises.get((int) id).setSelected(false);
    	    	}
    	    }

    	    /**
    	     * Delete or regenerate selected exercises.
    	     */
    	    @Override
    	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    	        // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                case R.id.delete:
                	
                	for (Exercise exercise : exercises){
                		if (exercise.isSelected()){
                			scheduleDAO.open();
                			scheduleDAO.deleteExerciseFromDay(day, exercise.getId());
                			scheduleDAO.close();
                		}
                	}
                	fillScreen(day);
                	
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                    
                case R.id.regenerate:
                	
                	for (Exercise exercise : exercises){
                		if (exercise.isSelected()){
                			scheduleDAO.open();
                			scheduleDAO.addExerciseToDay(day, (generator.generateExercise(exercise.getMainMuscle())).getId());
                			scheduleDAO.deleteExerciseFromDay(day, exercise.getId());
                			scheduleDAO.close();
                		}
                	}
                	fillScreen(day);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                    
                default:
                    return false;
                }
    	    }

    	    @Override
    	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
    	        // Inflate the menu for the CAB
    	        MenuInflater inflater = mode.getMenuInflater();
    	        inflater.inflate(R.menu.routine_menu, menu);
    	        return true;
    	    }

    	    @Override
    	    public void onDestroyActionMode(ActionMode mode) {
    	        // Here you can make any necessary updates to the activity when
    	        // the CAB is removed. By default, selected items are deselected/unchecked.
            	for (Exercise exercise : exercises){
            		if (exercise.isSelected()){
            			exercise.setSelected(false);
            		}
            	}
    	    }

    	    @Override
    	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
    	        // Here you can perform updates to the CAB due to
    	        // an invalidate() request
    	        return false;
    	    }
    	});
		
	}
    
    public void addExercise(View view){
    	new AlertDialog.Builder(this)
        .setTitle("Add Exercise")
        .setMessage("Choose a method to add exercises to your routine.")
        .setPositiveButton("Generate",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	Intent nextScreen = new Intent(getApplicationContext(), AddGeneratedExerciseToRoutineActivity.class);
            			nextScreen.putExtra("day", day);
            			startActivity(nextScreen);
                    	
                        dialog.cancel();
                    }
                })

        .setNeutralButton("Catalog",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    	//go to catalog, select exercises and come back on select
                    	Intent nextScreen = new Intent(getApplicationContext(), AddCatalogExerciseToRoutine.class);
            			nextScreen.putExtra("day", day);
            			startActivity(nextScreen);
            			
                    	dialog.cancel();
                    }
                })

        .setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
         .show();
    }
    
}
