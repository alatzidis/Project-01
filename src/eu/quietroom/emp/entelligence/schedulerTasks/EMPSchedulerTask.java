package eu.quietroom.emp.entelligence.schedulerTasks;

import java.util.ArrayList;

import eu.quietroom.emp.entelligence.EMPLog;
import eu.quietroom.emp.entelligence.intervalIterators.IntervalIterator;

public class EMPSchedulerTask{
	protected String taskName;
	protected IntervalIterator intervalIterator;
	protected ArrayList<EMPSchedulerTask> preconditionsList;
//	protected ArrayList<EMPSchedulerObject> subtasksList;
	
	public EMPSchedulerTask(String taskName, IntervalIterator intervalIterator){
		this(taskName, intervalIterator, new ArrayList<EMPSchedulerTask>());
	}
	
	public EMPSchedulerTask(String taskName, IntervalIterator intervalIterator, ArrayList<EMPSchedulerTask> preconditions){
		this.taskName = taskName;
		this.intervalIterator = intervalIterator;
		this.preconditionsList = preconditions;
	}
	
//	public void addSchedulerObject(EMPSchedulerObject so){
//		this.subtasksList.add(so);
//	}
	
	public boolean isCurrentCompleted(){
		return false;
	}
	
	public boolean hasError(){
		return false;
	}
	
	public boolean isReady(){
		for(EMPSchedulerTask precondition : preconditionsList){
			if(!precondition.isCurrentCompleted()){
				return false;
			}
		}
		return true;
	}
	
	public boolean hasPreconditionErrors(){
		for(EMPSchedulerTask prec : this.preconditionsList){
			if(prec.hasError()){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPassedStartTime(){
		return true;
	}
	
	public boolean hasPassedDeadLine(){
		return false;		
	}
	
	
	public String execute(){
		String out = "";
		return out;
	}
	
	public String getConsoleMsg(){
		if(isCurrentCompleted()){
			return "Task " + this.taskName + " completed.";
		}else{
			return "Task " + this.taskName + " failed.";
		}
	}
	/*
	public Object execute(){
		for(EMPSchedulerObject so : subtasksList){
			if(so instanceof EMPSchedulerTask){
				so.execute();				
			}else if(so instanceof EMPSchedulerAction){
				so.execute(null);
			}
		}
		return null;
	}
	*/
}
