package com.example.completlycustomgui;

import java.util.Random;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RelativeLayout view;
	private Random ran;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		ran = new Random();
		view = (RelativeLayout)findViewById(R.id.lemain);
	    ActionBar actionBar = getActionBar();
	    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
	        | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
		case R.id.action_settings:
			item.setActionView(R.id.prog);
			view.setBackgroundColor(Color.rgb(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)));
			Toast.makeText(getApplicationContext(), "Random Color!", Toast.LENGTH_SHORT).show();
			break;
		}
		
		return true;
	}

}
