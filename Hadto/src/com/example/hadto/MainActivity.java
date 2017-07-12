package com.example.hadto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int x;
	private MenuItem menuItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE| ActionBar.DISPLAY_SHOW_CUSTOM);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch(item.getItemId()){
		case R.id.menu_load:
			menuItem = item;
			ProgressBar prog = new ProgressBar(this);
			menuItem.setActionView(prog);
			menuItem.expandActionView();
			TestTask task = new TestTask();
			task.execute("test");
			break;
		default:
			break;
		}
		return true;
	}
	
	  private class TestTask extends AsyncTask<String, Void, String> {

		    @Override
		    protected String doInBackground(String... params) {
		      // Simulate something long running
		      try {
		        Thread.sleep(5000);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      return null;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		      menuItem.collapseActionView();
		      menuItem.setActionView(null);
		    }
		  };
}
