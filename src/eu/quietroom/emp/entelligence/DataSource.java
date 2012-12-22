package eu.quietroom.emp.entelligence;

import java.util.Calendar;
import java.util.Date;

import eu.quietroom.emp.entelligence.parsers.Parser;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class DataSource {
	private String url;
	private String fileNameSuffix;
	private int numOfDays;
	private String dateFormat;
	private boolean hasDateInName;
	private Parser parser;
	private String logMessage;
	
	public DataSource(String url, String fileNameSuffix, int numOfDays, String dateFormat, boolean hasDateInName, Parser parser, String logMessage){
		this.url = url;
		this.fileNameSuffix = fileNameSuffix;
		this.numOfDays = numOfDays;
		this.dateFormat = dateFormat;
		this.hasDateInName = hasDateInName;
		this.parser = parser;
		this.logMessage = logMessage;
	}
	
	public String getURL(){
		return this.url;
	}
	
	public String getFileNameSuffix(){
		return this.fileNameSuffix;
	}
	
	public int getNumOfDays(){
		return this.numOfDays;
	}
	
	public String getDateFormat(){
		return this.dateFormat;
	}
	
	public Parser getParser(){
		return this.parser;
	}
	
	public String getLogMessage(){
		return this.logMessage;
	}
	
	public DownloadData getDownloadDataByDate(){
		return getDownloadDataByDate(new Date());
	}
	
	public DownloadData getDownloadDataByDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar = DateUtils.setCalendarByDay(calendar, this.getNumOfDays());
		String dateString = DateUtils.convertToString(calendar.getTime(), this.getDateFormat());
		String url, filePath;
		if(hasDateInName){
			url = this.getURL() + dateString + this.getFileNameSuffix();
			filePath = EMPSettings.getExcelStoragePath() + dateString + this.getFileNameSuffix();
		}else{
			url = this.getURL() + this.getFileNameSuffix();
			filePath = EMPSettings.getExcelStoragePath() + this.getFileNameSuffix();
		}
		return new DownloadData(url, filePath, null);
	}
}
