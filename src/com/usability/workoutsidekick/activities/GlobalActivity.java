package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Provides global methods to every class, such as Home menu option.
 */
public class GlobalActivity extends Activity{
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.global_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.home:
	    		Intent nextScreen = new Intent(getApplicationContext(), HomeActivity.class);
	    		nextScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		startActivity(nextScreen);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
