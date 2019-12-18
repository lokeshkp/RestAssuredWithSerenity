package com.studentapp.utils;

import java.util.Random;

public class TestUtils {
	
	public static String getRandomValues(){
		Random random= new Random();
		int randInt = random.nextInt(100000);
		return Integer.toString(randInt);
	}

}
