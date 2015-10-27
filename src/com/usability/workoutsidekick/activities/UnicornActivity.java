package com.usability.workoutsidekick.activities;

import com.usability.workoutsidekick.R;
import com.usability.workoutsidekick.R.layout;
import com.usability.workoutsidekick.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class UnicornActivity extends GlobalActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unicorn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.unicorn, menu);
		return true;
	}

}
