package com.usability.workoutsidekick.activities;

import java.util.ArrayList;
import java.util.List;

import com.usability.workoutsidekick.Exercise;
import com.usability.workoutsidekick.ExerciseDAO;
import com.usability.workoutsidekick.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
 
public class UserActivity extends GlobalActivity{


	private ArrayList<Exercise> list = new ArrayList<Exercise>();
	private ArrayAdapter<Exercise> adapter;
	private ExerciseDAO dao;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        dao = new ExerciseDAO(this);
        
        populateListView();

        final ListView listview = (ListView) findViewById(R.id.listViewUser);
        listview.setTextFilterEnabled(true);
        adapter = new ArrayAdapter<Exercise>(this, R.layout.single_exercise, list);
        listview.setAdapter(adapter);
        
        registerForContextMenu(listview);
        
        listViewItemOnClick(listview);
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    populateListView();
    	adapter.notifyDataSetChanged();
	}
	
    public void populateListView() {
    	dao.open();
    	List<Exercise> exercises = dao.getUserAdded();
    	
    	list.clear();
    	for (Exercise e : exercises)
    		list.add(e);
    	
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
	
	/**
	 * Create popup menu and give functionality to selections.
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	                                ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.catalog_menu, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
	        case R.id.view:
	        	
				Intent nextScreen = new Intent(getApplicationContext(), DescriptionActivity.class);
				nextScreen.putExtra("id", list.get(info.position).getId());
				startActivity(nextScreen);
	            return true;
	            
	        case R.id.edit:
	        	
				Intent intent = new Intent(getApplicationContext(), EditActivity.class);
				intent.putExtra("id", list.get(info.position).getId());
				startActivity(intent);
	            return true;
	            
	        case R.id.delete:
	        	
    			dao.open();
    			dao.deleteExercise(list.get(info.position));
    			dao.close();
    			onResume();
	            return true;
	            
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
    
}
