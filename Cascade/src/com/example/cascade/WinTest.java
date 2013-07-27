package com.example.cascade;

import android.app.Activity;
import android.widget.ToggleButton;

public class WinTest extends Activity{
	public boolean test (ToggleButton position, ToggleButton color){

		if (position.getAlpha() == color.getAlpha() && 
				position.getX() == color.getX()){
				return true;
			}
		else{
			return false;
		}
	}
	public boolean sideScrollTest (ToggleButton position, ToggleButton color){

		if (position.getAlpha() == color.getAlpha() && 
				(position.getY() == color.getY())) {
				return true;
			}
		else{
			return false;
		}
	}

	
}