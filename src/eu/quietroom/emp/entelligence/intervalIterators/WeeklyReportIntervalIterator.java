package eu.quietroom.emp.entelligence.intervalIterators;

import java.util.Calendar;
import java.util.Date;

public class WeeklyReportIntervalIterator implements IntervalIterator{

//	private Date date;
	
	public WeeklyReportIntervalIterator(/*Date date*/){
//		this.date = date;
	}
	
	public Date getCurrentStart(){
		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
		setCalendarDay(cal);
		return cal.getTime();
	}
	
	public Date getNextStart(){
		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
		setCalendarDay(cal);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return cal.getTime();
	}
	
	public static void setCalendarDay(Calendar cal){
		cal.set(Calendar.DAY_OF_WEEK, 1);
		cal.set(Calendar.HOUR_OF_DAY, 11);
		cal.set(Calendar.MINUTE, 0);
	}

}
