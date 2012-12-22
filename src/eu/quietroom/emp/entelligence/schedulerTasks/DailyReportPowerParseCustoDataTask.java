package eu.quietroom.emp.entelligence.schedulerTasks;

import java.sql.SQLException;
import java.util.ArrayList;

import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataEntity;
import eu.quietroom.emp.entelligence.intervalIterators.DailyIntervalIterator;
import eu.quietroom.emp.entelligence.parsers.DailyPowerReportCustomDataParser;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class DailyReportPowerParseCustoDataTask extends EMPSchedulerTask{
	private String logLabel;
	private DailyPowerReportCustomDataParser parser;
	
	public DailyReportPowerParseCustoDataTask(ArrayList<EMPSchedulerTask> preconditions) {
		super("PARSE_DAILY_REPORT_POWER_CUSTOM_VALUES", new DailyIntervalIterator(14, 00, 16, 00), preconditions);
		this.logLabel = EMPSettings.t_p_daily_power_customValues;
		parser = new DailyPowerReportCustomDataParser();
	}
	
	public boolean isCurrentCompleted(){
		return EMPLog.hasCompletedEntry(super.intervalIterator.getCurrentStart(), logLabel);
	}
	
	public String execute(){
		String dateS = DateUtils.convertToString(super.intervalIterator.getNextStart(), "yyyyMMdd");
		parser.setParser(EMPSettings.settingsPath, dateS);
//		parser.gatherData();
		try {
			DataEntity.insertDataRowEntities(DBUnit.convertToDataRowEntity(parser.gatherData()));					
			EMPLog.addEntry(EMPSettings.t_p_daily_power_customValues, 1, super.intervalIterator.getCurrentStart(), 0, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}
