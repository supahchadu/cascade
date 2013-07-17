package com.example.cascade;

import android.widget.ToggleButton;
import java.util.Random;


public class Randomizer{

	int colorArray[] = {R.drawable.red_circle, R.drawable.green_circle, R.drawable.blue_circle};
	
	public void random (ToggleButton position1, ToggleButton position2, ToggleButton position3){

		int colorArray[] = {R.drawable.red_circle, R.drawable.green_circle, R.drawable.blue_circle};
		int randomColor, randomColor2, randomColor3 = colorArray[0];
		Random rand = new Random();

		do{
			randomColor = rand.nextInt(colorArray.length);
			position1.setButtonDrawable(colorArray[randomColor]);
			
			setAlpha(randomColor, position1);

			do{
				randomColor2 = rand.nextInt(colorArray.length);
				position2.setButtonDrawable(colorArray[randomColor2]);
				
				setAlpha(randomColor2, position2);
				
			}while(randomColor2 == randomColor);

			do{
				randomColor3 = rand.nextInt(colorArray.length);
				position3.setButtonDrawable(colorArray[randomColor3]);
				
				setAlpha(randomColor3, position3);
				
			}while(randomColor3 == randomColor || randomColor3 == randomColor2);

		}while(randomColor == 0 && randomColor2 == 1 && randomColor3 == 2);
	}
	
	public void setAlpha(int rc, ToggleButton pos){
		if (colorArray[rc] == R.drawable.red_circle){
			pos.setAlpha(100);
		}else if (colorArray[rc] == R.drawable.green_circle){
			pos.setAlpha(99);
		}else if (colorArray[rc] == R.drawable.blue_circle){
			pos.setAlpha(98);}
	}
}
		
		

