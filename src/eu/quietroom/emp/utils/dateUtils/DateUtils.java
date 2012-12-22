package eu.quietroom.emp.utils.dateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	private static final String defaultDateFormat = "dd/MM/yyyy";
	
	public static String convertToString(){
		return convertToString(new Date(), defaultDateFormat);
	}
	
	public static String convertToString(String dateFormat){
		return convertToString(new Date(), dateFormat);
	}
	
	public static String convertToString(Date date){
		return convertToString(date, defaultDateFormat);
	}
	
	public static String convertToString(Date date, String dateFormat){
		String out = "";
		SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat(dateFormat);
		out += dateformatYYYYMMDD.format(date);
		return out;
	}
	
	public static Date getDateByDay(int numOfDays){
		return getCalendarByDay(numOfDays).getTime();
	}
	
	public static Calendar getCalendarByDay(int numOfDays){
		Calendar calendar = Calendar.getInstance();
		setCalendarByDay(calendar, numOfDays);
		return calendar;
	}
	
	public static Calendar setCalendarByDay(Calendar calendar, int numOfDays){
		calendar.add(Calendar.DATE, numOfDays);
		return calendar;
	}
	
	public static void test(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(convertToString(calendar.getTime()));
		calendar.add(Calendar.DATE, 1);
		System.out.println(convertToString(calendar.getTime()));
	}
	
	public static Date getDateByString(String inputDate, String dateFormat){
		//String inputDate = "07/28/2011 11:06:37 AM";
		try {
			return new SimpleDateFormat(dateFormat).parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
