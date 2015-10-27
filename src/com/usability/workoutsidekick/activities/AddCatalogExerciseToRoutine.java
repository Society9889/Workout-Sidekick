package com.usability.workoutsidekick.activities;

import java.util.ArrayList;
import java.util.List;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.ListWithCheckboxAdapter;
import com.usability.workoutsidekick.R;
import com.usability.workoutsidekick.ScheduleDAO;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddCatalogExerciseToRoutine extends GlobalActivity{

	private ArrayList<Exercise> list = new ArrayList<Exercise>();
	private ArrayAdapter<Exercise> adapter;
	private ExerciseDAO dao;
	private ScheduleDAO scheduleDao;
	private String day;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_catalog_exercise_to_routine_activity);
        dao = new ExerciseDAO(this);
        scheduleDao = new ScheduleDAO(this);
        
        day = (String) getIntent().getExtras().get("day");
        
        populateListView();

        final ListView listview = (ListView) findViewById(R.id.listView);
        listview.setTextFilterEnabled(true);
        adapter = new ListWithCheckboxAdapter(this, list);
        listview.setAdapter(adapter);
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    populateListView();
    	adapter.notifyDataSetChanged();
	}
	
    public void populateListView() {
    	dao.open();
    	List<Exercise> exercises = dao.getAllExercises();
    	
    	list.clear();
    	for (Exercise e : exercises)
    		list.add(e);
    	
    	dao.close();
    }
    
	/**
	 * Add all checked exercises to a specific day.
	 */
	public void addExercises(View view){
		for (Exercise e : list){
			if (e.isSelected()){
				scheduleDao.open();
				scheduleDao.addExerciseToDay(day, e.getId());
				scheduleDao.close();
			}
		}
		
		this.onBackPressed();
	}
	
}
