package com.example.cascade;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Win2 extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.btcend);
		TextView score = (TextView) findViewById (R.id.btcend);
		TextView high = (TextView) findViewById (R.id.high_score);
		
		Intent mIntent = getIntent();
		int fScore = mIntent.getIntExtra("finalScore", 0);
		
		score.setText("Your Score: " + fScore);
		
		SharedPreferences settings = getSharedPreferences("hs", 0);
		int myvariable = settings.getInt("highScore", 0);
		
		high.setText("High Score: " + myvariable);
	}

}
