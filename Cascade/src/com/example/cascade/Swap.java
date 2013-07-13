package com.example.cascade;

import android.widget.ToggleButton;

public class Swap{
	
	public void swapButton(final ToggleButton origin,final ToggleButton colorDestination){
		
			if (origin.isChecked() && colorDestination.isChecked()){
				float temp = colorDestination.getX();
				colorDestination.setX(origin.getX());
				origin.setX(temp);
				origin.setChecked(false);
				colorDestination.setChecked(false);}		
	}
}	

