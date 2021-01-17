package com.pharmacy.nam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//use this class for check the validation 
public class Validation {
	//check length of string value and return boolean value
	static boolean check(String value, int max, int min) {
		if (value.length() <= max && value.length() >= min)
			return true;
		else
			return false;
	}
	
	//method for check integer value
		static boolean IsInteger(String value) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return false;
			} catch (NullPointerException e) {
				return false;
			}
			return true;
		}

		//method for check double value
		static boolean IsDouble(String value) {
			try {
				Double.parseDouble(value);
			} catch (NumberFormatException e) {
				return false;
			} catch (NullPointerException e) {
				return false;
			}
			return true;
		}
		
		//method for check date value
		static boolean IsDate(String dateToValidate, String dateFromat){
			
			if(dateToValidate == null){
				return false;
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);
			
			try {
				
				//if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
			
			} catch (ParseException e) {
				
				e.printStackTrace();
				return false;
			}
			return true;
		}
}
