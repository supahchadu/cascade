package com.example.cascade;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BTCGame extends Activity {
	
	CountDownTimer timer;
	int scoreCounter;
	int seconds = 30;
	double speedFactor = .48;
	TextView counter,scoreboard;
	ToggleButton redButton, greenButton, blueButton, leftButton, centerButton, rightButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.btcgame);
		
		initialize();
		
		//Randomizes the winning set at the bottom 
		final Randomizer winningSet = new Randomizer();
		winningSet.random(leftButton, centerButton, rightButton);
		
		//Set Speed Handler
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String secValues = getData.getString("speed", "1");
		
		if(secValues.contentEquals("1")){
			seconds = 30;
			speedFactor = .56 ;
		}else if (secValues.contentEquals("2")){
			seconds = 20;
			speedFactor = .85;
		}else if (secValues.contentEquals("3")){
			seconds = 10;
			speedFactor = 1.8;	
		}else if (secValues.contentEquals("4")){
			seconds = 5;
			speedFactor = 3.4;	
		}

		//High Score Handler
		SharedPreferences getData1 = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String resetHighScore = getData1.getString("reset", "1");
		
		if(resetHighScore.contentEquals("1")){
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(resetHighScore, "1");
			editor.commit();
		}else if (resetHighScore.contentEquals("2")){
				SharedPreferences settings = getSharedPreferences("hs", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("highScore", 0);
				editor.commit();
				
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor1 = prefs.edit();
				editor1.putString("reset", "1");
				editor1.commit();}
		
		
		//Controls the count down timer from 30 seconds and displays 
		//in 1 second intervals

		timer = new CountDownTimer((seconds*1000), 1) {
		    @Override
			public void onTick(long millisUntilFinished) {
		    	counter.setText("Time left " + millisUntilFinished / 1000);
		         
		        //each millisecond it ticks the circle drops by 1/speedFactor 
		        //of the height of the path
		        moveButtons(redButton);
		        moveButtons(greenButton);
		        moveButtons(blueButton);
		         
		        Swap swap = new Swap();
		         
		        swap.swapButton(greenButton, redButton);
		        swap.swapButton(blueButton, redButton);
		        swap.swapButton(redButton, greenButton);
		        swap.swapButton(blueButton, greenButton);
		        swap.swapButton(redButton, blueButton);
		        swap.swapButton(greenButton, blueButton);
		         
		 		WinTest winner = new WinTest();
				if(winner.test(leftButton,redButton) &&
				   winner.test(centerButton, blueButton) &&
				   winner.test(rightButton, greenButton)||
				   winner.test(leftButton,redButton) &&
				   winner.test(centerButton, greenButton) &&
				   winner.test(rightButton, blueButton)||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerButton, redButton) &&
				   winner.test(rightButton, blueButton) ||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerButton, blueButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerButton, redButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerButton, greenButton) &&
				   winner.test(rightButton, redButton)){
					
					if(leftButton.isChecked()||centerButton.isChecked()||
					   rightButton.isChecked()){
						
						scoreCounter++;
						scoreboard.setText("Score: " + scoreCounter);
						resetScoreButtons();
						winningSet.random(leftButton, centerButton, rightButton);}
				}else if(leftButton.isChecked()||centerButton.isChecked()||
						 rightButton.isChecked()){
									
					scoreboard.setText("Ooops!");
					resetScoreButtons();
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {
							scoreboard.setText("Score: " + scoreCounter);}
						}, 1000);
					}
		    }
		    
		@Override
		public void onFinish() {	
			
			SharedPreferences settings1 = getSharedPreferences("hs", 0);
			int high = settings1.getInt("highScore", 0);
				
			if (high < scoreCounter){
				SharedPreferences settings = getSharedPreferences("hs", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putInt("highScore", scoreCounter);
				editor.commit();}
				
				counter.setText("done!");
		    
				Intent sendScore = new Intent(BTCGame.this, Win2.class);
				sendScore.putExtra("finalScore", scoreCounter);
				startActivity(sendScore);}
			
		}.start();
	}
		
	public void initialize(){
		scoreboard = (TextView) findViewById (R.id.score);
		counter = (TextView) findViewById (R.id.countdown);
		redButton = (ToggleButton) findViewById (R.id.red);
		greenButton = (ToggleButton) findViewById (R.id.green);
		blueButton = (ToggleButton) findViewById (R.id.blue);
		leftButton = (ToggleButton) findViewById (R.id.leftButton);
		centerButton = (ToggleButton) findViewById (R.id.centerButton);
		rightButton = (ToggleButton) findViewById (R.id.rightButton);
	}
	
	//method that moves the circle down the path
	public void moveButtons (ToggleButton color){
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		
		final TextView path = (TextView) findViewById (R.id.colorPath);	  
		path.setY(height);
		
		if (color.getY()< path.getBottom()){
			color.setY((float) (color.getY()+ speedFactor));
		}		
	}
	
	public void resetScoreButtons (){
		leftButton.setChecked(false);
		centerButton.setChecked(false);
		rightButton.setChecked(false);
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.in_game_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){

		case R.id.new_game:
		    Intent intent = getIntent();
		    overridePendingTransition(0, 0);
		    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		    finish();
			timer.cancel();
		    overridePendingTransition(0, 0);
		    startActivity(intent);
			break;

		case R.id.main_menu:
			timer.cancel();
		    finish();
			break;
			
		case R.id.exit:
			timer.cancel();
			finish();
			System.exit(0);
			break;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		timer.cancel();
		finish();	
	}
}
