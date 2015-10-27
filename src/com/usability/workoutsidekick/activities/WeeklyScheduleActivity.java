package com.usability.workoutsidekick.activities;

import java.util.ArrayList;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ListWithDayPreviewAdapter;
import com.usability.workoutsidekick.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WeeklyScheduleActivity extends GlobalActivity{

	private ArrayAdapter<String> adapter;
	private ListView listview;
	private String[] list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weekly_schedule_activity);
		listview = (ListView) findViewById(R.id.daysList);
		listViewItemOnClick();
		
		list = getResources().getStringArray(R.array.Days);
        listview.setTextFilterEnabled(true);
        adapter = new ListWithDayPreviewAdapter(this, list);
        listview.setAdapter(adapter);
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    adapter = new ListWithDayPreviewAdapter(this, list);
	    listview.setAdapter(adapter);
    	adapter.notifyDataSetChanged();
	}
	
	/**
	 * Go to the routine screen for the selected day.
	 */
	public void listViewItemOnClick() {
		final ListView listview = (ListView) findViewById(R.id.daysList);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		      @Override
		      public void onItemClick(AdapterView<?> parent, final View view,
		    		  int position, long id) {
		    	  
		    	 String[] days =  getResources().getStringArray(R.array.Days);
		    	 String currentSelection = days[(int) id];
		    	  
				 Intent nextScreen = new Intent(getApplicationContext(), RoutineActivity.class);
				 nextScreen.putExtra("day", currentSelection);
				 startActivity(nextScreen);
		      }
		 });
	}
	
}
