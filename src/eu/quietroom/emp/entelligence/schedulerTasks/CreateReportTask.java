package eu.quietroom.emp.entelligence.schedulerTasks;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.birt.BirtIntegration;
import eu.quietroom.emp.entelligence.intervalIterators.IntervalIterator;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class CreateReportTask extends EMPSchedulerTask{
	private String logLabel;

	public CreateReportTask(String taskName, IntervalIterator intervalIterator,	ArrayList<EMPSchedulerTask> preconditions, String logLabel) {
		super(taskName, intervalIterator, preconditions);
		this.logLabel = logLabel;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isCurrentCompleted(){
		return EMPLog.hasCompletedEntry(super.intervalIterator.getCurrentStart(), logLabel);
	}
	
	public String execute(){
		String out = "";
		if(!isCurrentCompleted()){
			Date logDate = super.intervalIterator.getCurrentStart();
//			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//			String dateString = myFormat.format(super.intervalIterator.getCurrentStart());
			String dateFormatString = "yyyy-MM-dd";
			if(EMPSettings.t_c_report_power_daily.equals(this.logLabel)){
		    	BirtIntegration.createReport(BirtIntegration.EMP_DAILY_POWER, DateUtils.convertToString(DateUtils.getDateByDay(1), dateFormatString));
		    	EMPLog.addEntry(this.logLabel, 1, logDate, 0, "");
			}else if(EMPSettings.t_c_report_power_weekly.equals(this.logLabel)){
				BirtIntegration.createReport(BirtIntegration.EMP_WEEKLY_POWER, DateUtils.convertToString(DateUtils.getDateByDay(0), dateFormatString));
		    	EMPLog.addEntry(this.logLabel, 1, logDate, 0, "");
			}else if(EMPSettings.t_c_report_emissions_weekly.equals(this.logLabel)){
				BirtIntegration.createReport(BirtIntegration.EMP_WEEKLY_EMMISSIONS, DateUtils.convertToString(DateUtils.getDateByDay(0), dateFormatString));
		    	EMPLog.addEntry(this.logLabel, 1, logDate, 0, "");
			}
		}
		return out;
	}

}
