package de.vogella.android.temperature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.CheckBox;


public class MainActivity extends Activity {

	RelativeLayout myV;
	private EditText text;
	private CheckBox CheckBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		myV = (RelativeLayout) findViewById(R.id.backi);
		text = (EditText) findViewById(R.id.editText1);
		CheckBox = (CheckBox) findViewById(R.id.AutoChange);
		

	}
	
	public void onClick(View view) {
		RadioButton celsiusButton = (RadioButton) findViewById(R.id.radio0);
		RadioButton fahrenheitButton = (RadioButton) findViewById(R.id.radio1);
		if(text.getText().length() == 0) {
			Toast.makeText(this, "Please enter a valid number",
		            Toast.LENGTH_SHORT).show();
			myV.setBackground(getWallpaper());
		} else{
			float inputValue = Float.parseFloat(text.getText().toString());
			if(celsiusButton.isChecked()) {
				text.setText(String.valueOf(((inputValue - 32) * 5 / 9)));
				if(CheckBox.isChecked()){
				fahrenheitButton.setChecked(true);
				celsiusButton.setChecked(false);}
			} else {
				text.setText(String.valueOf(((inputValue * 9) / 5) + 32));
				if(CheckBox.isChecked()){
				fahrenheitButton.setChecked(false);
		        celsiusButton.setChecked(true);}
				
				
			}
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}
