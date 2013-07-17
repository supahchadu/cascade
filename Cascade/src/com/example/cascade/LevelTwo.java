package com.example.cascade;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ToggleButton;

public class LevelTwo extends Activity {

	CountDownTimer timer;
	int seconds = 30;
	double speedFactor = .48;
	TextView counter;
	ToggleButton redButton, greenButton, blueButton, leftButton, centerButton, rightButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leveltwo);

		initialize();

		// Randomizes the winning set at the bottom
		Randomizer winningSet = new Randomizer();
		
		//winning set for level 1
		winningSet.random(leftButton, centerButton, rightButton);

		//Speed of the Balls
		SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String secValues = getData.getString("speed", "1");

		if (secValues.contentEquals("1")) {
			seconds = 30;
			speedFactor = .48;
		} else if (secValues.contentEquals("2")) {
			seconds = 20;
			speedFactor = .70;
		} else if (secValues.contentEquals("3")) {
			seconds = 10;
			speedFactor = 1.5;
		} else if (secValues.contentEquals("4")) {
			seconds = 5;
			speedFactor = 3.2;
		}

		// Controls the count down timer from 30 seconds and displays
		// in 1 second intervals
		timer = new CountDownTimer((seconds * 1000), 1) {
			@Override
			public void onTick(long millisUntilFinished) {
				counter.setText("Time left " + millisUntilFinished / 1000);

				// each millisecond it ticks the circle drops by 1/speedFactor
				// of the height of the path
				
				//moveButtons(redButton);
				//moveButtons(greenButton);
				//moveButtons(blueButton);
				
				moveButtonsLevel_Two(redButton);
				moveButtonsLevel_Two(greenButton);
				moveButtonsLevel_Two(blueButton);

				Swap swap = new Swap();

				swap.swapButton(greenButton, redButton);
				swap.swapButton(blueButton, redButton);
				swap.swapButton(redButton, greenButton);
				swap.swapButton(blueButton, greenButton);
				swap.swapButton(redButton, blueButton);
				swap.swapButton(greenButton, blueButton);

			}

			@Override
			public void onFinish() {

				WinTest winner = new WinTest();

				if (winner.test(leftButton, redButton)
						&& winner.test(centerButton, blueButton)
						&& winner.test(rightButton, greenButton)
						
						|| winner.test(leftButton, redButton)
						&& winner.test(centerButton, greenButton)
						&& winner.test(rightButton, blueButton)
						|| winner.test(leftButton, greenButton)
						&& winner.test(centerButton, redButton)
						&& winner.test(rightButton, blueButton)
						|| winner.test(leftButton, greenButton)
						&& winner.test(centerButton, blueButton)
						&& winner.test(rightButton, redButton)
						|| winner.test(leftButton, blueButton)
						&& winner.test(centerButton, redButton)
						&& winner.test(rightButton, greenButton)
						|| winner.test(leftButton, blueButton)
						&& winner.test(centerButton, greenButton)
						&& winner.test(rightButton, redButton)) {
					
					

					Intent win = new Intent("com.example.cascade.WIN");
					startActivity(win);
					
			
					
			

				} else {

					Intent lose = new Intent("com.example.cascade.LOSE");
					startActivity(lose);
				}
				counter.setText("done!");
				
				
				
				
			}
		}.start();
	}

	public void initialize() {
		counter = (TextView) findViewById(R.id.countdown);
		counter.setTextColor(Color.parseColor("#FFFFFF"));
		redButton = (ToggleButton) findViewById(R.id.red);
		greenButton = (ToggleButton) findViewById(R.id.green);
		blueButton = (ToggleButton) findViewById(R.id.blue);
		leftButton = (ToggleButton) findViewById(R.id.leftButton);
		centerButton = (ToggleButton) findViewById(R.id.centerButton);
		rightButton = (ToggleButton) findViewById(R.id.rightButton);
	}
	

	// method that moves the circle down the path
	//For Level 1
	/*
	public void moveButtons(ToggleButton color) {

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;

		final TextView path = (TextView) findViewById(R.id.colorPath);
		path.setY(height);

		if (color.getY() < path.getBottom()) {
			color.setY((float) (color.getY() + speedFactor));
		}
	}
	*/
	public void moveButtonsLevel_Two(ToggleButton color){
		
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;

		final TextView path = (TextView) findViewById(R.id.colorPath);
		path.setX(width);

		if (color.getX() < path.getRight()) {
			color.setX((float) (color.getX() + speedFactor));
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.in_game_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

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
