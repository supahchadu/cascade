package com.example.cascade;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{

	MediaPlayer openingSong;
	
	@Override
	protected void onCreate(Bundle SplashScreen) {
		super.onCreate(SplashScreen);	
		setContentView(R.layout.splash);
		
		openingSong = MediaPlayer.create(Splash.this, R.raw.splash_music);
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean introMusicState = getPrefs.getBoolean("introMusic", true);
		if(introMusicState == true){
		openingSong.start();}
		
		
		Thread timer = new Thread(){
			
			@Override
			public void run(){
				try{
					sleep(7000);

				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.example.cascade.MENU");
					startActivity(openStartingPoint);
				}
				
			}//end run()
		};//end Thread
		timer.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		openingSong.release();
		finish();
	}
	
}
