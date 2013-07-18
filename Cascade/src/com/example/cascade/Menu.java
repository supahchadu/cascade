package com.example.cascade;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity implements View.OnClickListener{
  
	Button b1;
	TextView tv1;
	String gOption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		Button startGame = (Button) findViewById (R.id.button1);
		Button prefButton = (Button) findViewById (R.id.preferences); 
		Button exit = (Button) findViewById (R.id.exit);
		
		startGame.setOnClickListener(this);
		prefButton.setOnClickListener(this);
		exit.setOnClickListener(this);		
	}

	@Override
	public void onClick(View v) {
	
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String gameOption = getData.getString("gameOption", "1");
		
		if (gameOption.contentEquals("1")){
			gOption = "GAME";
		}else if (gameOption.contentEquals("2")){
			gOption = "BTCGAME";
		}else if (gameOption.contentEquals("3")){
			gOption = "LEVELTWO";
		}else if (gameOption.contentEquals("4")){
			gOption = "CONTINUOUS";
		}
		
		switch(v.getId()){
		case R.id.button1:
			Intent newGame = new Intent("com.example.cascade." + gOption);
			startActivity(newGame);
			break;
			
		case R.id.preferences:
			Intent prefMenu = new Intent("com.example.cascade.PREFERENCES");
			startActivity(prefMenu);
			break;
		
		case R.id.exit:
			finish();
			System.exit(0);
			break;
		}
	}
}