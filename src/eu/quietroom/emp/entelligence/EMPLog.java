package eu.quietroom.emp.entelligence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.dbaccess.LogEntity;
import eu.quietroom.emp.entelligence.dbaccess.LogRowEntity;

public class EMPLog {
	
	public static boolean addEntry(String entity, int complete, Date date, int error, String comment){
		try {
			LogEntity.insertLogRowEntity(new LogRowEntity(entity, complete, date, error, comment));
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean hasCompletedEntry(Date date, String entity){
		try {
			ArrayList<LogRowEntity> lreList = LogEntity.selectLogRowEntity(date, entity);
			for(LogRowEntity lre : lreList){
				if(lre.getComplete() == 1){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean hasErrorEntry(Date date, String entity){
		try {
			ArrayList<LogRowEntity> lreList = LogEntity.selectLogRowEntity(date, entity);
			for(LogRowEntity lre : lreList){
				if(lre.getError() == 1){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
