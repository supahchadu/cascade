package com.example.cascade;

import android.os.Bundle;
import android.os.CountDownTimer;
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

public class Game2 extends Activity {

	CountDownTimer timer;
	int seconds = 30;
	
	double speedFactor = .48;
	TextView counter;
	ToggleButton redButton, greenButton, blueButton, purpleButton, leftButton,
				centerLeftButton, centerRightButton, rightButton;
	@Override
	protected void onCreate (Bundle SavedInstanceState2)
	{	
		super.onCreate (SavedInstanceState2);
		setContentView(R.layout.game2);
		
		initialize();
		
		//Randomize the winning set at bottom of page
		Randomizer2 winningSet = new Randomizer2();
		winningSet.random(leftButton, centerLeftButton, centerRightButton, rightButton);
		
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
		         moveButtons(purpleButton);
		         
		         Swap swap = new Swap();
		         
		         swap.swapButton(greenButton, redButton);
		         swap.swapButton(greenButton, purpleButton);
		         swap.swapButton(greenButton, blueButton);
		         swap.swapButton(blueButton, redButton);
		         swap.swapButton(blueButton, greenButton);
		         swap.swapButton(blueButton, purpleButton);
		         swap.swapButton(redButton, greenButton);
		         swap.swapButton(redButton, blueButton);
		         swap.swapButton(redButton, purpleButton);
		         swap.swapButton(purpleButton, redButton);
		         swap.swapButton(purpleButton, blueButton);
		         swap.swapButton(purpleButton, greenButton);
		         
		                 
		    }
		    
			@Override
			public void onFinish() {
				
				WinTest winner = new WinTest();
				
				if(winner.test(leftButton,redButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton,redButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton, redButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton, redButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, blueButton) ||
				   winner.test(leftButton, redButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, blueButton) ||
				   winner.test(leftButton, redButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, greenButton) ||

				   winner.test(leftButton, blueButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton,blueButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton, blueButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, redButton) ||
				   
				   winner.test(leftButton,greenButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton,greenButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, blueButton) ||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, purpleButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, purpleButton) ||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, greenButton) &&
				   winner.test(centerLeftButton, purpleButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, blueButton) ||
				   
				   winner.test(leftButton,purpleButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton,purpleButton) &&
				   winner.test(centerLeftButton, redButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, blueButton) ||
				   winner.test(leftButton, purpleButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, greenButton) ||
				   winner.test(leftButton, purpleButton) &&
				   winner.test(centerLeftButton, blueButton) &&
				   winner.test(centerRightButton, greenButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, purpleButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, blueButton) &&
				   winner.test(rightButton, redButton) ||
				   winner.test(leftButton, purpleButton) &&
				   winner.test(centerLeftButton, greenButton) &&
				   winner.test(centerRightButton, redButton) &&
				   winner.test(rightButton, blueButton)
				   ){
				   
					Intent win = new Intent("com.example.cascade.WIN");
					startActivity(win);					
					
				}else{
					
					Intent lose = new Intent("com.example.cascade.LOSE");
					startActivity(lose);}
					
				  				   
		    counter.setText("done!");
		    }
		}.start();
	}
	
public void initialize(){
	counter = (TextView) findViewById (R.id.countdown);
	redButton = (ToggleButton) findViewById (R.id.red);
	greenButton = (ToggleButton) findViewById (R.id.green);
	blueButton = (ToggleButton) findViewById (R.id.blue);
	purpleButton = (ToggleButton) findViewById (R.id.purple);
	leftButton = (ToggleButton) findViewById (R.id.leftButton);
	centerLeftButton = (ToggleButton) findViewById (R.id.centerLeftButton);
	centerRightButton = (ToggleButton) findViewById (R.id.centerRightButton);
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

