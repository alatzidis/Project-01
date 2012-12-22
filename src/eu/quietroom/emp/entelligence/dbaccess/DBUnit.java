package eu.quietroom.emp.entelligence.dbaccess;

import java.util.ArrayList;

public class DBUnit {
	
	public static ArrayList<DataRowEntity> convertToDataRowEntity(ArrayList<DBUnit> rowList){
		ArrayList<DataRowEntity> out = new ArrayList<DataRowEntity>();
		for(DBUnit r : rowList){
			if(r instanceof DataRowEntity){
				out.add((DataRowEntity)r);
			}
		}
		return out;
	}
	
	public static ArrayList<UnitRowEntity> convertToUnitRowEntity(ArrayList<DBUnit> rowList){
		ArrayList<UnitRowEntity> out = new ArrayList<UnitRowEntity>();
		for(DBUnit r : rowList){
			if(r instanceof UnitRowEntity){
				out.add((UnitRowEntity)r);
			}
		}
		return out;
	}
	
	public static ArrayList<LogRowEntity> convertToLogRowEntity(ArrayList<DBUnit> rowList){
		ArrayList<LogRowEntity> out = new ArrayList<LogRowEntity>();
		for(DBUnit r : rowList){
			if(r instanceof LogRowEntity){
				out.add((LogRowEntity)r);
			}
		}
		return out;
	}

}
