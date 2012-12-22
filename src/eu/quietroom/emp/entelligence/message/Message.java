package eu.quietroom.emp.entelligence.message;

import java.util.Date;

import eu.quietroom.emp.utils.dateUtils.DateUtils;


public abstract class Message{
//	private static String defaultType = "DEFAULT MESSAGE TYPE";
	private String messageType;
	private Date date;
	
	public Message(String messageType){
		this.messageType = messageType;
		this.date = new Date();
	}
	
	public String print(){
		String out = "";
		out += "MESSAGE TYPE: " + this.messageType + "\n";
		out += "DATE OF MESSAGE: " + DateUtils.convertToString(date) + "\n"; 
		return out;
	}
}
