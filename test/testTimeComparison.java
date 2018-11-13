package com.group99.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class testTimeComparison {

	@Test
	public void testTimeComparison() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");

		Date mDate = dateFormat.parse("14:50");
		Date currentDate = dateFormat.parse(dateFormat.format(new Date()));
		
		if(mDate.getTime() < currentDate.getTime()){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}

	}
}
