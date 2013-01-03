package eu.quietroom.emp.entelligence;

import java.util.ArrayList;

import eu.quietroom.emp.entelligence.dbaccess.DBTools;
import eu.quietroom.emp.entelligence.intervalIterators.DailyIntervalIterator;
import eu.quietroom.emp.entelligence.intervalIterators.WeeklyReportIntervalIterator;
import eu.quietroom.emp.entelligence.parsers.BNS_STATS_parser;
import eu.quietroom.emp.entelligence.parsers.DAS_R_Parser;
import eu.quietroom.emp.entelligence.parsers.DAS_UA_Parser;
import eu.quietroom.emp.entelligence.parsers.SettingsParser;
import eu.quietroom.emp.entelligence.parsers.WA_LF_Parser;
import eu.quietroom.emp.entelligence.parsers.WA_WUD_Parser;
import eu.quietroom.emp.entelligence.parsers.WeatherForecast_Parser;
import eu.quietroom.emp.entelligence.schedulerTasks.CreateReportTask;
import eu.quietroom.emp.entelligence.schedulerTasks.DailyDownloadTask;
import eu.quietroom.emp.entelligence.schedulerTasks.DailyParseTask;
import eu.quietroom.emp.entelligence.schedulerTasks.DailyReportPowerParseCustoDataTask;
import eu.quietroom.emp.entelligence.schedulerTasks.EMPSchedulerTask;
import eu.quietroom.emp.entelligence.schedulerTasks.WeeklyReportEmissionsParseCustoDataTask;
import eu.quietroom.emp.entelligence.schedulerTasks.WeeklyReportPowerParseCustoDataTask;
import eu.quietroom.emp.utils.dateUtils.DateUtils;


public class EMPScheduler implements Runnable{
//	private EMPDownloader empDownloader;
	private int heartBeat;
	private ArrayList<EMPSchedulerTask> tasksList;
	private DBTools dbTool;
	
	private DataSource DAS_R_Source;
	private DailyDownloadTask task_dl_DAS_R;
	private DataSource DAS_UA_Source;
	private DailyDownloadTask task_dl_DAS_UA;
	private DataSource WA_WUD_Source;
	private DailyDownloadTask task_dl_WA_WUD;
	private DataSource WA_LF_Source;
	private DailyDownloadTask task_dl_WA_LF;
	private DataSource WeatherForecast_Source;
	private DailyDownloadTask task_dl_WeatherForecast;
	private DataSource BNS_STATS_Source;
	private DailyDownloadTask task_dl_BNS_STATS;
	
	private DailyParseTask task_p_DAS_R;
	private DailyParseTask task_p_DAS_UA;
	private DailyParseTask task_p_WA_WUD;
	private DailyParseTask task_p_WA_LF;
	private DailyParseTask task_p_BNS_STATS;
	private DailyParseTask task_p_Weather;
	
	private DailyReportPowerParseCustoDataTask task_p_daily_power_customData;
	private WeeklyReportPowerParseCustoDataTask task_p_weekly_power_customData;
	private WeeklyReportEmissionsParseCustoDataTask task_p_weekly_emissions_customData;
	
	private CreateReportTask task_c_power_daily;
	private CreateReportTask task_c_power_weekly;
	private CreateReportTask task_c_emissions_weekly;

//	private ArrayList<DataSource> dataSourceList;
	
	public EMPScheduler(String settingsPath){
		this(settingsPath, 300000);
	}

	public EMPScheduler(String settingsPath, int waitForDownload){
		this.heartBeat = waitForDownload;
//		this.dataSourceList = new ArrayList<DataSource>();
		loadSettings(settingsPath);
		initDB();
		setSchedulerTasks();
//		setDataSources();
//		this.empDownloader = new EMPDownloader(downloadList);
	}
	
	private void initDB(){
		dbTool = new DBTools();
		try {
			dbTool.startTool();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void setSchedulerTasks(){
		tasksList = new ArrayList<EMPSchedulerTask>();

		DAS_R_Source = new DataSource(EMPSettings.getDAS_R_URL(), EMPSettings.getDAS_R_Suffix(), (int)Float.parseFloat(EMPSettings.getDAS_R_DaysAhead()), EMPSettings.getDAS_R_DateFormat(), true, new DAS_R_Parser(), EMPSettings.t_d_DAS_R);
		DAS_UA_Source = new DataSource(EMPSettings.getDAS_UA_URL(), EMPSettings.getDAS_UA_FileSuffix(), (int)Float.parseFloat(EMPSettings.getDAS_UA_DaysAhead()), EMPSettings.getDAS_UA_DateFormat(), true, new DAS_UA_Parser(), EMPSettings.t_d_DAS_UA);
		WA_WUD_Source = new DataSource(EMPSettings.getDAS_WA_URL(), EMPSettings.getDAS_WA_suffix(), (int)Float.parseFloat(EMPSettings.getDAS_WA_DaysAhead()), EMPSettings.getDAS_WA_DateFormat(), true, new WA_WUD_Parser(), EMPSettings.t_d_WA_WUD);
		WA_LF_Source = new DataSource(EMPSettings.getDAS_LF_1_5_URL(), EMPSettings.getDAS_LF_1_5_Suffix(), (int)Float.parseFloat(EMPSettings.getDAS_LF_1_5_DaysAhead()), EMPSettings.getDAS_LF_1_5_DateFormat(), true, new WA_LF_Parser(), EMPSettings.t_d_WA_LF);
		BNS_STATS_Source = new DataSource(EMPSettings.getBNS_STATS_3_1_URL(), EMPSettings.getBNS_STATS_3_1_FileSuffix(), (int)Float.parseFloat(EMPSettings.getBNS_STATS_3_1_DaysAhead()), EMPSettings.getBNS_STATS_3_1_DateFormat(), true, new BNS_STATS_parser(), EMPSettings.t_d_BNS_STATS);
		WeatherForecast_Source = new DataSource(EMPSettings.getWeatherForecast_URL(), EMPSettings.getWeatherForecast_suffix(), (int)Float.parseFloat(EMPSettings.getWeatherForecast_daysAhead()), EMPSettings.getDAS_R_DateFormat(), false, new WeatherForecast_Parser(), EMPSettings.t_d_weather);
		
		task_dl_DAS_R = new DailyDownloadTask("DOWNLOAD_DAS_R", DAS_R_Source, EMPSettings.getDAS_R_1_1StartHour(), EMPSettings.getDAS_R_1_1StartMinute(), EMPSettings.getDAS_R_1_1EndHour(), EMPSettings.getDAS_R_1_1EndMinute());
		tasksList.add(task_dl_DAS_R);

		task_dl_DAS_UA = new DailyDownloadTask("DOWNLOAD_DAS_UA", DAS_UA_Source, EMPSettings.getDAS_UA_1_3StartHour(), EMPSettings.getDAS_UA_1_3StartMinute(), EMPSettings.getDAS_UA_1_3EndHour(), EMPSettings.getDAS_UA_1_3EndMinute());
		tasksList.add(task_dl_DAS_UA);

		task_dl_WA_WUD = new DailyDownloadTask("DOWNLOAD_WA_WUD", WA_WUD_Source, EMPSettings.getDAS_WU_1_4_StartHour(), EMPSettings.getDAS_WU_1_4_StartMinute(), EMPSettings.getDAS_WU_1_4_EndHour(), EMPSettings.getDAS_WU_1_4_EndMinute());
		tasksList.add(task_dl_WA_WUD);
		
		task_dl_WA_LF = new DailyDownloadTask("DOWNLOAD_WA_LF", WA_LF_Source, EMPSettings.getDAS_LF_1_5_StartHour(), EMPSettings.getDAS_LF_1_5_StartMinute(), EMPSettings.getDAS_LF_1_5_EndHour(), EMPSettings.getDAS_LF_1_5_EndMinute());
		tasksList.add(task_dl_WA_LF);
		
		task_dl_BNS_STATS = new DailyDownloadTask("DOWNLOAD_BNS_STATS", BNS_STATS_Source, EMPSettings.getBNS_STATS_3_1_StartHour(), EMPSettings.getBNS_STATS_3_1_StartMinute(), EMPSettings.getBNS_STATS_3_1_EndHour(), EMPSettings.getBNS_STATS_3_1_EndMinute());
		tasksList.add(task_dl_BNS_STATS);
		
		task_dl_WeatherForecast = new DailyDownloadTask("DOWNLOAD_WEATHER", WeatherForecast_Source, EMPSettings.getWeatherForecast_StartHour(), EMPSettings.getWeatherForecast_StartMinute(), EMPSettings.getWeatherForecast_EndHour(), EMPSettings.getWeatherForecast_EndMinute());
		tasksList.add(task_dl_WeatherForecast);
		
		ArrayList<EMPSchedulerTask> task_p_DAS_R_precs = new ArrayList<EMPSchedulerTask>();
		task_p_DAS_R_precs.add(task_dl_DAS_R);
		task_p_DAS_R =  new DailyParseTask("PARSE_DAS_R", DAS_R_Source, EMPSettings.getDAS_R_1_1StartHour(), EMPSettings.getDAS_R_1_1StartMinute(), EMPSettings.getDAS_R_1_1EndHour(), EMPSettings.getDAS_R_1_1EndMinute(), task_p_DAS_R_precs);
		tasksList.add(task_p_DAS_R);
		
		ArrayList<EMPSchedulerTask> task_p_DAS_UA_precs = new ArrayList<EMPSchedulerTask>();
		task_p_DAS_UA_precs.add(task_dl_DAS_UA);
		task_p_DAS_UA = new DailyParseTask("PARSE_DAS_UA", DAS_UA_Source, EMPSettings.getDAS_UA_1_3StartHour(), EMPSettings.getDAS_UA_1_3StartMinute(), EMPSettings.getDAS_UA_1_3EndHour(), EMPSettings.getDAS_UA_1_3EndMinute(), task_p_DAS_UA_precs);
		tasksList.add(task_p_DAS_UA);
		
		ArrayList<EMPSchedulerTask> task_p_WA_WUD_precs = new ArrayList<EMPSchedulerTask>();
		task_p_WA_WUD_precs.add(task_dl_WA_WUD);
		task_p_WA_WUD = new DailyParseTask("PARSE_WA_WUD", WA_WUD_Source, EMPSettings.getDAS_WU_1_4_StartHour(), EMPSettings.getDAS_WU_1_4_StartMinute(), EMPSettings.getDAS_WU_1_4_EndHour(), EMPSettings.getDAS_WU_1_4_EndMinute(), task_p_WA_WUD_precs);
		tasksList.add(task_p_WA_WUD);
		
		ArrayList<EMPSchedulerTask> task_p_WA_LF_precs = new ArrayList<EMPSchedulerTask>();
		task_p_WA_LF_precs.add(task_dl_WA_LF);
		task_p_WA_LF = new DailyParseTask("PARSE_WA_LF", WA_LF_Source, EMPSettings.getDAS_LF_1_5_StartHour(), EMPSettings.getDAS_LF_1_5_StartMinute(), EMPSettings.getDAS_LF_1_5_EndHour(), EMPSettings.getDAS_LF_1_5_EndMinute(), task_p_WA_LF_precs);
		tasksList.add(task_p_WA_LF);
		
		ArrayList<EMPSchedulerTask> task_p_BNS_STATS_precs = new ArrayList<EMPSchedulerTask>();
		task_p_BNS_STATS_precs.add(task_dl_BNS_STATS);
		task_p_BNS_STATS = new DailyParseTask("PARSE_BNS_STATS", BNS_STATS_Source, EMPSettings.getBNS_STATS_3_1_StartHour(), EMPSettings.getBNS_STATS_3_1_StartMinute(), EMPSettings.getBNS_STATS_3_1_EndHour(), EMPSettings.getBNS_STATS_3_1_EndMinute(), task_p_BNS_STATS_precs);
		tasksList.add(task_p_BNS_STATS);
		
		ArrayList<EMPSchedulerTask> task_p_Weather_precs = new ArrayList<EMPSchedulerTask>();
		task_p_Weather_precs.add(task_dl_WeatherForecast);
		task_p_Weather = new DailyParseTask("PARSE_WEATHER", WeatherForecast_Source, EMPSettings.getWeatherForecast_StartHour(), EMPSettings.getWeatherForecast_StartMinute(), EMPSettings.getWeatherForecast_EndHour(), EMPSettings.getWeatherForecast_EndMinute(), task_p_Weather_precs);
		tasksList.add(task_p_Weather);
		
		ArrayList<EMPSchedulerTask> task_p_daily_power_customData_precs = new ArrayList<EMPSchedulerTask>();
		task_p_daily_power_customData_precs.add(task_p_DAS_R);
		task_p_daily_power_customData_precs.add(task_p_DAS_UA);
		task_p_daily_power_customData_precs.add(task_p_WA_WUD);
		task_p_daily_power_customData_precs.add(task_dl_WA_LF);
		task_p_daily_power_customData = new DailyReportPowerParseCustoDataTask(task_p_daily_power_customData_precs);
		tasksList.add(task_p_daily_power_customData);
		
		ArrayList<EMPSchedulerTask> task_p_weekly_power_customData_precs = new ArrayList<EMPSchedulerTask>();
		task_p_weekly_power_customData = new WeeklyReportPowerParseCustoDataTask(task_p_weekly_power_customData_precs);
		tasksList.add(task_p_weekly_power_customData);
		
		ArrayList<EMPSchedulerTask> task_p_weekly_emissions_customData_precs = new ArrayList<EMPSchedulerTask>();
		task_p_weekly_emissions_customData = new WeeklyReportEmissionsParseCustoDataTask(task_p_weekly_emissions_customData_precs);
		tasksList.add(task_p_weekly_emissions_customData);
		
		ArrayList<EMPSchedulerTask> task_c_power_daily_precs = new ArrayList<EMPSchedulerTask>();
		task_c_power_daily_precs.add(task_p_DAS_R);
		task_c_power_daily_precs.add(task_p_DAS_UA);
		task_c_power_daily_precs.add(task_p_WA_WUD);
		task_c_power_daily_precs.add(task_p_WA_LF);
		task_c_power_daily_precs.add(task_p_Weather);
		task_c_power_daily_precs.add(task_p_daily_power_customData);
		task_c_power_daily = new CreateReportTask("CREATE_DAILY_POWER_REPORT", new DailyIntervalIterator(EMPSettings.getDAS_R_1_1StartHour(), EMPSettings.getDAS_R_1_1StartMinute(), EMPSettings.getDAS_R_1_1EndHour(), EMPSettings.getDAS_R_1_1EndMinute()), task_c_power_daily_precs, EMPSettings.t_c_report_power_daily);
		tasksList.add(task_c_power_daily);
		
		ArrayList<EMPSchedulerTask> task_c_power_weekly_precs = new ArrayList<EMPSchedulerTask>();
		task_c_power_weekly = new CreateReportTask("CREATE_WEEKLY_POWER_REPORT", new WeeklyReportIntervalIterator(), task_c_power_weekly_precs, EMPSettings.t_c_report_power_weekly);
		tasksList.add(task_c_power_weekly);
		
		ArrayList<EMPSchedulerTask> task_c_emissions_weekly_precs = new ArrayList<EMPSchedulerTask>();
		task_c_emissions_weekly = new CreateReportTask("CREATE_WEEKLY_EMISSIONS_REPORT", new WeeklyReportIntervalIterator(), task_c_emissions_weekly_precs, EMPSettings.t_c_report_emissions_weekly);
		tasksList.add(task_c_emissions_weekly);
		
	}
	
	@Override
	public void run(){
		while(true){
			System.out.println("EMPScheduler heart beat at " + DateUtils.convertToString("dd MMM yyyy HH:mm:ss.SSS"));
			for(EMPSchedulerTask task : tasksList){
				if(task.isReady() && !task.isCurrentCompleted() && task.hasPassedStartTime()){
					try {
						task.execute();
					}catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println("\t" + task.getConsoleMsg());
				}
			}
			try {
				Thread.sleep(this.heartBeat);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadSettings(String settingsPath){
		SettingsParser ps = new SettingsParser();
		ps.setParser(settingsPath, "");
    	ps.gatherData();
	}
}
