package com.example.cascade;

import android.app.Activity;
import android.widget.ToggleButton;

public class WinSideScrollTest extends Activity{
	public boolean test (ToggleButton position, ToggleButton color){

		if (position.getAlpha() == color.getAlpha() && 
				(position.getY() == color.getY())) {
				return true;
			}
		else{
			return false;
		}
	}
}