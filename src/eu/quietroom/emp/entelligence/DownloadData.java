package eu.quietroom.emp.entelligence;

import java.util.Calendar;

import eu.quietroom.emp.entelligence.parsers.Parser;

public class DownloadData {
	private String url;
	private String savePath;
	private Calendar timeOfPublication;
	private boolean isCompleted;
	
	public DownloadData(String url, String savePath, Calendar timeOfPublication){
		this.url = url;
		this.savePath = savePath;
		this.timeOfPublication = timeOfPublication;
		this.isCompleted = false;
	}
	
	public String getURL(){
		return this.url;
	}
	
	public String getSavePath(){
		return this.savePath;
	}
	
	public Calendar getTimeOfPublication(){
		return this.timeOfPublication;
	}
	
	public void setIsCompleted(boolean val){
		this.isCompleted = val;
	}
	
	public boolean getIsCompleted(){
		return this.isCompleted;
	}
	
	@Override
	public String toString(){
		String out = "";
		out += "URL: " + this.url;
		return out;
	}
}
