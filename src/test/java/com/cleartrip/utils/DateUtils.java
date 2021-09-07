package com.cleartrip.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date returnDate(int noOfDaysFromCurrentDate) {
		Calendar myCalendar = Calendar.getInstance();
		myCalendar.add(Calendar.DATE, noOfDaysFromCurrentDate);
		
		return myCalendar.getTime();
		
	}

	public static int returnDay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
		return Integer.parseInt(strDate.split("/")[0]);
	}

	public static String returnMonth(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
		int month =  Integer.parseInt(strDate.split("/")[1]);
		
		switch(month) {
		case 1 :
			return "January";
		case 2 :
			return "February";
		case 3 :
			return "March";
		case 4 :
			return "April";
		case 5 :
			return "May";
		case 6 :
			return "June";
		case 7 :
			return "July";
		case 8 :
			return "August";
		case 9 :
			return "September";
		case 10 :
			return "October";
		case 11 :
			return "November";
		case 12 :
			return "December";
		default :
			return "";

		}
	}

	public static int returnYear(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String strDate= formatter.format(date);  
		int year =  Integer.parseInt(strDate.split("/")[2]);
		return year;
	}

}
