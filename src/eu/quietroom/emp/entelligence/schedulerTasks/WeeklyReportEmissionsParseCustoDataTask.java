package eu.quietroom.emp.entelligence.schedulerTasks;

import java.sql.SQLException;
import java.util.ArrayList;

import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataEntity;
import eu.quietroom.emp.entelligence.intervalIterators.DailyIntervalIterator;
import eu.quietroom.emp.entelligence.parsers.WeeklyEmissionsReportCustomDataParser;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class WeeklyReportEmissionsParseCustoDataTask extends EMPSchedulerTask{
	private String logLabel;
	private WeeklyEmissionsReportCustomDataParser parser;
	
	public WeeklyReportEmissionsParseCustoDataTask(ArrayList<EMPSchedulerTask> preconditions) {
		super("PARSE_WEEKLY_REPORT_EMISSIONS_CUSTOM_VALUES", new DailyIntervalIterator(14, 00, 16, 00), preconditions);
		this.logLabel = EMPSettings.t_p_weekly_emissions_customValues;
		parser = new WeeklyEmissionsReportCustomDataParser();
	}
	
	public boolean isCurrentCompleted(){
		return EMPLog.hasCompletedEntry(super.intervalIterator.getCurrentStart(), logLabel);
	}
	
	public String execute(){
		String dateS = DateUtils.convertToString(super.intervalIterator.getCurrentStart(), "yyyyMMdd");
		parser.setParser(EMPSettings.settingsPath, dateS);
//		parser.gatherData();
		try {
			DataEntity.insertDataRowEntities(DBUnit.convertToDataRowEntity(parser.gatherData()));					
			EMPLog.addEntry(logLabel, 1, super.intervalIterator.getCurrentStart(), 0, "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}
