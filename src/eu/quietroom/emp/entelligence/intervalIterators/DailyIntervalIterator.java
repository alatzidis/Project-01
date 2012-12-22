package eu.quietroom.emp.entelligence.intervalIterators;

import java.util.Calendar;
import java.util.Date;

public class DailyIntervalIterator implements IntervalIterator{
//	private Date date;
	private int startHour;
	private int startMinute;
	private int endHour;
	private  int endMinute;
	
	
	public DailyIntervalIterator(/*Date date, */int startHour, int startMinute, int endHour, int endMinute){
//		this.date = date;
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
	}
	
	public Date getCurrentStart(){
		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.setTime(new Date());		
		setCalendarHour(cal, startHour, startMinute);
		return cal.getTime();
	}
	
	public Date getCurrentDeadline(){
		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.setTime(new Date());		
		setCalendarHour(cal, endHour, endMinute);
		return cal.getTime();
	}
	
	public Date getNextStart(){
		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//		cal.setTime(new Date());		
		setCalendarHour(cal, startHour, startMinute);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}
	
	public static void setCalendarHour(Calendar cal, int hour, int minute){
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
	}
}
