package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;

import eu.quietroom.emp.entelligence.dbaccess.DBUnit;

public interface Parser {
	
	public void setParser(String filePath, String dateString);
	
	public ArrayList<DBUnit> gatherData();
	
	public String getLogMessage();
	
	public String getDBType();

}