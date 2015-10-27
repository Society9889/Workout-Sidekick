package com.usability.workoutsidekick.activities;

import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.usability.workoutsidekick.R;
 
public class TabLayoutActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Photos
        TabSpec allspec = tabHost.newTabSpec("All");
        allspec.setIndicator("All");
        Intent photosIntent = new Intent(this, AllActivity.class);
        allspec.setContent(photosIntent);
 
        // Tab for Songs
        TabSpec favoritesspec = tabHost.newTabSpec("Favorites");
        favoritesspec.setIndicator("Favorites");
        Intent songsIntent = new Intent(this, FavoritesActivity.class);
        favoritesspec.setContent(songsIntent);
 
        // Tab for Videos
        TabSpec userspec = tabHost.newTabSpec("User Created");
        userspec.setIndicator("User Created");
        Intent videosIntent = new Intent(this, UserActivity.class);
        userspec.setContent(videosIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(allspec);		// Adding all tab
        tabHost.addTab(favoritesspec);	// Adding favorites tab
        tabHost.addTab(userspec);		// Adding user tab
        
        SearchView searchView = (SearchView) findViewById(R.id.searchView1);
        
        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                // Do something
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
        		Intent nextScreen = new Intent(getApplicationContext(), ResultsActivity.class);
    			nextScreen.putExtra("query", query);
        		startActivity(nextScreen);
            	//Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);

    }

    
	public void goToCreateExercise(View view){
		Intent nextScreen = new Intent(getApplicationContext(), CreateExcersiseActivity.class);
		startActivity(nextScreen);
	}
}