package com.example.realproject;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class PageViewActivity extends FragmentActivity {

	  MyPageAdapter pageAdapter;

	  @Override

	  public void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);

	    setContentView(R.layout.activity_main);

	    List<Fragment> fragments = getFragments();

	    pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

	    ViewPager pager = (ViewPager)findViewById(R.id.viewpager);

	    pager.setAdapter(pageAdapter);

	  }

	}


}
