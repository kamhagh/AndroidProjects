package com.example.testing;

import com.example.bestpractices.R;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

   private Button Check;
   private BatteryManager battery;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Check = (Button)findViewById(R.id.button1);
   }

   public void check(View view){
      IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
      Intent batteryStatus = registerReceiver(null, ifilter);
      int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
      boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
      status == BatteryManager.BATTERY_STATUS_FULL;
      // How are we charging?
      int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED,
      -1);
      boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
      boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

      if(usbCharge){
         Toast.makeText(getApplicationContext(),"Mobile is charging on USB",Toast.LENGTH_LONG).show();
      }
      else{
         Toast.makeText(getApplicationContext(),"Mobile is charging on AC",Toast.LENGTH_LONG).show();
      }

   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

}