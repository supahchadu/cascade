package com.example.cascade;

import android.widget.ToggleButton;
import java.util.Random;

public class Randomizer{

	public void random (ToggleButton position1, ToggleButton position2, ToggleButton position3){
		
		int colorArray[] = {R.drawable.red_circle, R.drawable.green_circle, R.drawable.blue_circle};
		
		Random rand = new Random();
		
		int randomColor, randomColor2, randomColor3;
		
		randomColor = rand.nextInt(colorArray.length);
		position1.setButtonDrawable(colorArray[randomColor]);
		
		if (colorArray[randomColor] == R.drawable.red_circle){
			position1.setAlpha(100);
		}else if (colorArray[randomColor] == R.drawable.green_circle){
			position1.setAlpha(99);
		}else if (colorArray[randomColor] == R.drawable.blue_circle){
			position1.setAlpha(98);
		}
		
		do{
			randomColor2 = rand.nextInt(colorArray.length);
			position2.setButtonDrawable(colorArray[randomColor2]);}
		while(randomColor2 == randomColor);
		
		if (colorArray[randomColor2] == R.drawable.red_circle){
			position2.setAlpha(100);
		}else if (colorArray[randomColor2] == R.drawable.green_circle){
			position2.setAlpha(99);
		}else if (colorArray[randomColor2] == R.drawable.blue_circle){
			position2.setAlpha(98);
		}
		
		do{
			randomColor3 = rand.nextInt(colorArray.length);
			position3.setButtonDrawable(colorArray[randomColor3]);}
		while(randomColor3 == randomColor || randomColor3 == randomColor2);
		
		if (colorArray[randomColor3] == R.drawable.red_circle){
			position3.setAlpha(100);
		}else if (colorArray[randomColor3] == R.drawable.green_circle){
			position3.setAlpha(99);
		}else if (colorArray[randomColor3] == R.drawable.blue_circle){
			position3.setAlpha(98);
		}
	}
	
}
