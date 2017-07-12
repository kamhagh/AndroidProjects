package com.example.minesota;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final int NOTIF_ID = 1234;
		 
		 NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		 Notification note = new Notification(R.drawable.ic_launcher, "New E-mail", System.currentTimeMillis());
		 
		 PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, Main.class), 0);
		 
		 note.setLatestEventInfo(this, "New E-mail", "You have one unread message.", intent);
		 
		 notifManager.notify(NOTIF_ID, note);
	 // notifManager.cancel(NOTIF_ID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
