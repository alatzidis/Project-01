package eu.quietroom.emp.entelligence.schedulerTasks;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import eu.quietroom.emp.entelligence.DataSource;
import eu.quietroom.emp.entelligence.DownloadData;
import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataEntity;
import eu.quietroom.emp.entelligence.dbaccess.UnitEntity;
import eu.quietroom.emp.entelligence.intervalIterators.DailyIntervalIterator;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class DailyParseTask extends EMPSchedulerTask{
	protected DataSource ds;
	
	public DailyParseTask(String taskName, DataSource ds, int startHour, int startMinute, int endHour, int endMinute, ArrayList<EMPSchedulerTask> preconditions){
		super(taskName, new DailyIntervalIterator(startHour, startMinute, endHour, endMinute), preconditions);
		this.ds = ds;
	}
	
	@Override
	public boolean isCurrentCompleted(){
		return EMPLog.hasCompletedEntry(super.intervalIterator.getCurrentStart(), ds.getParser().getLogMessage());
	}
	
	public String execute(){
		String out = "";
		if(!isCurrentCompleted()){
			if(!this.hasPreconditionErrors()){
				DownloadData dd = ds.getDownloadDataByDate(super.intervalIterator.getCurrentStart());
				Calendar cal = Calendar.getInstance();
				cal.setTime(super.intervalIterator.getCurrentStart());
				DateUtils.setCalendarByDay(cal, ds.getNumOfDays());
				String dateS = DateUtils.convertToString(cal.getTime(), ds.getDateFormat());
				this.ds.getParser().setParser(dd.getSavePath(), dateS);
				try {
					if(EMPSettings.DB_TYPE_DATA.equals(ds.getParser().getDBType())){
						DataEntity.insertDataRowEntities(DBUnit.convertToDataRowEntity(this.ds.getParser().gatherData()));					
					}else if(EMPSettings.DB_TYPE_UNIT.equals(ds.getParser().getDBType())){
						UnitEntity.insertUnitRowEntities(DBUnit.convertToUnitRowEntity(this.ds.getParser().gatherData()));										
					}
					EMPLog.addEntry(ds.getParser().getLogMessage(), 1, super.intervalIterator.getCurrentStart(), 0, "");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}else{
				EMPLog.addEntry(ds.getParser().getLogMessage(), 1, super.intervalIterator.getCurrentStart(), 1, "Parse Failed Due to Download Task Fail");				
			}
		}
		return out;
	}
}
