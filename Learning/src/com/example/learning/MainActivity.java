package com.example.learning;

import android.os.Bundle;
import android.view.View;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends Activity {
	
	private int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      final ActionBar actionBar = getActionBar();
      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
      actionBar.setDisplayShowTitleEnabled(false);
      actionBar.setDisplayShowHomeEnabled(false);
      setContentView(R.layout.activity_main);
      
      count = 0;
      
	}
	 
	public void selectFrag(View view) {
		 Fragment fr;
		 
		 if(count == 0) {
			 fr = new FragmentTwo();
			 count = 1;
		 }else {
			 fr = new FragmentOne();
			 count = 0;
		 }
		 
		 FragmentManager fm = getFragmentManager();
	     FragmentTransaction fragmentTransaction = fm.beginTransaction();
	     fragmentTransaction.replace(R.id.fragment_place, fr);
	     fragmentTransaction.commit();
		 
	}
   
}