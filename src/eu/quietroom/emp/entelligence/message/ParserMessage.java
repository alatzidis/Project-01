package eu.quietroom.emp.entelligence.message;

import eu.quietroom.emp.entelligence.EMPSettings;

public class ParserMessage extends Message{
	public ParserMessage(){
		super(EMPSettings.getParserMessage());
	}
	
	public String print(){
		String out = "";
		out += super.print();
		out += "sdfsadfsdff";
		return out;
	}
}
