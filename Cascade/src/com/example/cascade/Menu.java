package com.example.cascade;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Menu extends Activity implements View.OnClickListener {

	Button b1;
	TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		tv1 = (TextView) findViewById(R.id.textView1);
		Button startGame = (Button) findViewById(R.id.button1);
		Button prefButton = (Button) findViewById(R.id.preferences);
		Button exit = (Button) findViewById(R.id.exit);

		// changes textview color to white
		tv1.setTextColor(Color.parseColor("#FFFFFF"));

		startGame.setOnClickListener(this);
		prefButton.setOnClickListener(this);
		exit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			Intent newGame = new Intent("com.example.cascade.STARTINGPOINT");
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