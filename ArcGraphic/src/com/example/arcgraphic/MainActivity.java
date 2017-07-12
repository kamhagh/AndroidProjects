package com.example.arcgraphic;

import com.example.learning.Arc;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private Arc graph;
	private SeekBar seek;
	private ToggleButton TB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	      /*requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
	        
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		
		graph = (Arc)findViewById(R.id.graphics1);
		seek = (SeekBar)findViewById(R.id.seekBar);
		TB = (ToggleButton)findViewById(R.id.TB);
		seek.setMax(200);
		
		seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override 
			   public void onProgressChanged(SeekBar seekBar, int progress, 
			     boolean fromUser) { 
				//if(progress % 5 == 0)
					graph.setSpacing(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				
				//if(seek.getProgress() % 5 == 0)
					graph.setSpacing(seek.getProgress());
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void AntiAliasturn(View view)
	{
		graph.setAntiAlias(TB.isChecked());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	    }

	 
}
