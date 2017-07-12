package com.example.threads;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar bar1;
	private ProgressBar bar2;
	private ProgressBar bar3;
	private ProgressBar bar4;
	private static Handler handler;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bar1 = (ProgressBar) findViewById(R.id.progressBar1);
		bar1.setMax(1000);
		bar2 = (ProgressBar) findViewById(R.id.ProgressBar2);
		bar2.setMax(500);
		bar3 = (ProgressBar) findViewById(R.id.ProgressBar3);
		bar3.setMax(200);
		bar4 = (ProgressBar) findViewById(R.id.ProgressBar4);
		bar4.setMax(100);
		
		Notification n  = new Notification.Builder(this);
		NotificationManager notificationManager = 
				  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(0, n); 
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.arg2){
				case 1:
					bar1.setProgress(msg.arg1);
					break;
				case 2:
					bar2.setProgress(msg.arg1);
					break;
				case 3:
					bar3.setProgress(msg.arg1);
					break;
				case 4:
					bar4.setProgress(msg.arg1);
					break;
				}
				if(msg.what != 0)
					Toast.makeText(getApplicationContext(), "Thread " + msg.what + " finished!", Toast.LENGTH_SHORT).show();				
			}
		};
	}

	public void startProgress(View view) {

		switch(view.getId()){
		case R.id.button1:
			bar1.setProgress(0);
			new Thread(new Task1()).start();
			break;
		case R.id.Button2:
			bar2.setProgress(0);
			new Thread(new Task2()).start();
			break;
		case R.id.Button3:
			bar3.setProgress(0);
			new Thread(new Task3()).start();
		case R.id.button4:
			bar4.setProgress(0);
			new Thread(new Task4()).start();
		}
	}

	class Task1 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 1000; i++) {
				final int value = i;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.arg1 = i;
				msg.arg2 = 1;
				handler.sendMessage(msg);

			}
			handler.sendEmptyMessage(1);
		}

	}
	class Task2 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 500; i++) {
				final int value = i;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.arg1 = i;
				msg.arg2 = 2;
				handler.sendMessage(msg);

			}
			handler.sendEmptyMessage(2);
		}

	}
	class Task3 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 200; i++) {
				final int value = i;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.arg1 = i;
				msg.arg2 = 3;
				handler.sendMessage(msg);
			}
			handler.sendEmptyMessage(3);
		}

	}
	class Task4 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i <= 100; i++) {
				final int value = i;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.arg1 = i;
				msg.arg2 = 4;
				handler.sendMessage(msg);

			}
			handler.sendEmptyMessage(4);
		}

	}

}