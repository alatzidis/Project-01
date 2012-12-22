package eu.quietroom.emp.entelligence.schedulerTasks;

import java.util.Calendar;

import eu.quietroom.emp.entelligence.DataSource;
import eu.quietroom.emp.entelligence.DownloadData;
import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.intervalIterators.DailyIntervalIterator;
import eu.quietroom.emp.utils.downloadUtils.DownloadUtils;

public class DailyDownloadTask extends EMPSchedulerTask{
	protected DataSource ds;
	private DailyIntervalIterator dailyIterator;
	
	public DailyDownloadTask(String taskName, DataSource ds, int startHour, int startMinute, int endHour, int endMinute){
		super(taskName, new DailyIntervalIterator(startHour, startMinute, endHour, endMinute));

		dailyIterator = (DailyIntervalIterator) super.intervalIterator;

		this.ds = ds;
	}
	
	@Override
	public boolean isCurrentCompleted(){
		return EMPLog.hasCompletedEntry(super.intervalIterator.getCurrentStart(), ds.getLogMessage());
	}
	
	@Override
	public boolean hasError(){
		return EMPLog.hasErrorEntry(super.intervalIterator.getCurrentStart(), ds.getLogMessage());
	}
	
	@Override
	public boolean hasPassedStartTime(){
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(dailyIterator.getCurrentStart());
		Calendar currentTime = Calendar.getInstance();
		if(startTime.after(currentTime)){
			return false;
		}else{
			return true;			
		}
	}
	
	@Override
	public boolean hasPassedDeadLine(){
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(dailyIterator.getCurrentDeadline());
		Calendar currentTime = Calendar.getInstance();
		if(endTime.after(currentTime)){
			return false;
		}else{
			return true;			
		}

	}
	
	public String execute(){
		String out = "";
		if(!isCurrentCompleted()){
			DownloadData dd = ds.getDownloadDataByDate(super.intervalIterator.getCurrentStart());
			if(DownloadUtils.downloadFile(dd.getURL(), dd.getSavePath())){
				EMPLog.addEntry(ds.getLogMessage(), 1, super.intervalIterator.getCurrentStart(), 0, "");
			}else if(this.hasPassedDeadLine()){
				System.out.println("DEADLINE!!!");
				EMPLog.addEntry(ds.getLogMessage(), 1, super.intervalIterator.getCurrentStart(), 1, "Deadline Passed - Download Failed");				
			}
		}
		return out;
	}
}
