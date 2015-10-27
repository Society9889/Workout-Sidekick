package com.usability.workoutsidekick.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.R;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ResultsActivity extends GlobalActivity{

	private ArrayList<Exercise> list = new ArrayList<Exercise>();
	private ArrayAdapter<Exercise> adapter;
	private ExerciseDAO dao;
	private String query;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
        setContentView(R.layout.all_activity);
        dao = new ExerciseDAO(this);
        
        query = (String) getIntent().getExtras().get("query");
        populateResultsView(query);

        final ListView listview = (ListView) findViewById(R.id.listViewAll);
        listview.setTextFilterEnabled(true);
        adapter = new ArrayAdapter<Exercise>(this, R.layout.single_exercise, list);
        listview.setAdapter(adapter);
        
        listViewItemOnClick(listview);
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    populateResultsView(query);
    	adapter.notifyDataSetChanged();
	}
	
    public void populateResultsView(String query) {
    	Pattern pattern = Pattern.compile(query.toLowerCase());
    	dao.open();
    	List<Exercise> exercises = dao.getAllExercises();
    	
    	list.clear();
    	for (Exercise e : exercises){
    		Matcher match = pattern.matcher(e.getName().toLowerCase());
    		if (match.find()){
    			list.add(e);
    		}
    	}
    	dao.close();
    }
    
	public void listViewItemOnClick(ListView listview) {
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Intent nextScreen = new Intent(getApplicationContext(), DescriptionActivity.class);
				nextScreen.putExtra("id", list.get((int)id).getId());
				startActivity(nextScreen);
			}
		});
	}
}