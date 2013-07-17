package com.example.cascade;

import android.widget.ToggleButton;
import java.util.Random;


public class Randomizer{

public void random (ToggleButton position1, ToggleButton position2, ToggleButton position3)
{

int colorArray[] = {R.drawable.red_circle, R.drawable.green_circle, R.drawable.blue_circle};

Random rand = new Random();

int randomColor, randomColor2, randomColor3 = colorArray[0];

do{
randomColor = rand.nextInt(colorArray.length);
position1.setButtonDrawable(colorArray[randomColor]);

do{
randomColor2 = rand.nextInt(colorArray.length);
position2.setButtonDrawable(colorArray[randomColor2]);
}while(randomColor2 == randomColor);

do{
randomColor3 = rand.nextInt(colorArray.length);
position3.setButtonDrawable(colorArray[randomColor3]);
}while(randomColor3 == randomColor || randomColor3 == randomColor2);

}while(randomColor == 0 && randomColor2 == 1 && randomColor3 == 2);

}

}

