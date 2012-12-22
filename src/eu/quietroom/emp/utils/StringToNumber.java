package eu.quietroom.emp.utils;

public class StringToNumber {
	public static boolean isInt(String str){
		try{
	        Integer.parseInt(str);
	        return true;
	    }catch(NumberFormatException nfe){
	        return false;
	    }
	}
	
	public static boolean isNumber(String str){
		try{
	        Float.parseFloat(str);
	        return true;
	    }catch(NumberFormatException nfe){
	        return false;
	    }
	}
}