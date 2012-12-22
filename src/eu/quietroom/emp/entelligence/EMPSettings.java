package eu.quietroom.emp.entelligence;

import java.util.ArrayList;

public class EMPSettings {
	public static final String settingsPath = "files/system/settings.xlsx";
	/**
	 * DB names
	 */
	public static final String dbSMP = Messages.getString("EMPSettings.0"); //$NON-NLS-1$
	public static final String dbLoad = Messages.getString("EMPSettings.1"); //$NON-NLS-1$
	public static final String dbLoss = Messages.getString("EMPSettings.2"); //$NON-NLS-1$
	public static final String dbWaterUsageDeclaration = Messages.getString("EMPSettings.3"); //$NON-NLS-1$
	
	public static final String dbWeatherPrefix = Messages.getString("EMPSettings.22"); //$NON-NLS-1$
	public static final String dbTemperature = Messages.getString("EMPSettings.23"); //$NON-NLS-1$
	public static final String dbTempMin = Messages.getString("EMPSettings.24"); //$NON-NLS-1$
	public static final String dbTempMax = Messages.getString("EMPSettings.25"); //$NON-NLS-1$
	public static final String dbWindSpeed = Messages.getString("EMPSettings.26"); //$NON-NLS-1$
	public static final String dbChanceOfPrecipitation = Messages.getString("EMPSettings.27"); //$NON-NLS-1$
	public static final String dbSunnyHours = Messages.getString("EMPSettings.28"); //$NON-NLS-1$
	
	public static final String dbLoadForecast = Messages.getString("EMPSettings.4"); //$NON-NLS-1$
	public static final String dbUnitPrefix = Messages.getString("EMPSettings.5"); //$NON-NLS-1$
	public static final String dbLignite = Messages.getString("EMPSettings.6"); //$NON-NLS-1$
	public static final String dbHydro = Messages.getString("EMPSettings.7"); //$NON-NLS-1$
	public static final String dbCCGT = Messages.getString("EMPSettings.8"); //$NON-NLS-1$
	public static final String dbRenewables = Messages.getString("EMPSettings.9"); //$NON-NLS-1$
	public static final String dbImportsExports = Messages.getString("EMPSettings.10"); //$NON-NLS-1$
	public static final String dbDivider = Messages.getString("EMPSettings.11"); //$NON-NLS-1$
	
	public static final String dbBNSPrefix = Messages.getString("EMPSettings.31"); //$NON-NLS-1$	
	public static final String dbBNS_CER = Messages.getString("EMPSettings.32"); //$NON-NLS-1$	
	public static final String dbBNS_CER_LH = Messages.getString("EMPSettings.33"); //$NON-NLS-1$	
	public static final String dbBNSSpread_outright_BNS_CER_EUA = Messages.getString("EMPSettings.34"); //$NON-NLS-1$	
	public static final String dbBNS_ERU = Messages.getString("EMPSettings.35"); //$NON-NLS-1$	
	public static final String dbBNS_ERU_LH = Messages.getString("EMPSettings.36"); //$NON-NLS-1$	
	public static final String dbBNS_EUA_08_12 = Messages.getString("EMPSettings.37"); //$NON-NLS-1$	
	public static final String dbBNS_EUAA = Messages.getString("EMPSettings.38"); //$NON-NLS-1$	
	public static final String dbBNS_GREEN_CER = Messages.getString("EMPSettings.39"); //$NON-NLS-1$	

	public static final String dbBNS_Daily_Closing_Price = Messages.getString("EMPSettings.40"); //$NON-NLS-1$	
	public static final String dbBNS_Variation = Messages.getString("EMPSettings.41"); //$NON-NLS-1$	
	public static final String dbBNS_Listed_Volume = Messages.getString("EMPSettings.42"); //$NON-NLS-1$	
	public static final String dbBNS_Otc_Volume = Messages.getString("EMPSettings.43"); //$NON-NLS-1$	
	public static final String dbBNS_Total_Volume = Messages.getString("EMPSettings.44"); //$NON-NLS-1$	
	
	public static final String dbDailyPower_top = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.52"); //$NON-NLS-1$	
	public static final String dbDailyPower_bottom = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.53"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bd1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.54"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pd1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.55"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_od1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.56"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bwa = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.57"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pwa = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.58"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_owa = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.59"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bw1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.60"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pw1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.61"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_ow1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.62"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bw2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.63"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pw2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.64"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_ow2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.65"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bm2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.66"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pm2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.67"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_om2 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.68"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_bm3 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.69"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_pm3 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.70"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_om3 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.71"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_by1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.72"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_py1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.73"); //$NON-NLS-1$	
	public static final String dbDailyPower_array_oy1 = Messages.getString("EMPSettings.51") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.75") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.74"); //$NON-NLS-1$	
	
	public static final String dbWeeklyPower_comments = Messages.getString("EMPSettings.77"); //$NON-NLS-1$
	public static final String dbWeeklyEmissions_comments = Messages.getString("EMPSettings.78"); //$NON-NLS-1$

	public static final String t_d_DAS_R = Messages.getString("EMPSettings.12"); //$NON-NLS-1$
	public static final String t_d_DAS_UA = Messages.getString("EMPSettings.13"); //$NON-NLS-1$
	public static final String t_d_WA_WUD = Messages.getString("EMPSettings.14"); //$NON-NLS-1$
	public static final String t_d_WA_LF = Messages.getString("EMPSettings.15"); //$NON-NLS-1$
	public static final String t_d_weather = Messages.getString("EMPSettings.20"); //$NON-NLS-1$
	public static final String t_d_BNS_STATS = Messages.getString("EMPSettings.29"); //$NON-NLS-1$
	
	public static final String t_p_DAS_R = Messages.getString("EMPSettings.16"); //$NON-NLS-1$
	public static final String t_p_DAS_UA = Messages.getString("EMPSettings.17"); //$NON-NLS-1$
	public static final String t_p_WA_WUD = Messages.getString("EMPSettings.18"); //$NON-NLS-1$
	public static final String t_p_WA_LF = Messages.getString("EMPSettings.19"); //$NON-NLS-1$
	public static final String t_p_weather = Messages.getString("EMPSettings.21"); //$NON-NLS-1$
	public static final String t_p_BNS_STATS = Messages.getString("EMPSettings.30"); //$NON-NLS-1$
	public static final String t_p_daily_power_customValues = Messages.getString("EMPSettings.76"); //$NON-NLS-1$
	public static final String t_p_weekly_power_customValues = Messages.getString("EMPSettings.79"); //$NON-NLS-1$
	public static final String t_p_weekly_emissions_customValues = Messages.getString("EMPSettings.80"); //$NON-NLS-1$
	
	public static final String t_c_report_power_daily = Messages.getString("EMPSettings.50") + Messages.getString("EMPSettings.45") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.46") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.48"); //$NON-NLS-1$	
	public static final String t_c_report_power_weekly = Messages.getString("EMPSettings.50") + Messages.getString("EMPSettings.45") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.46") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.49"); //$NON-NLS-1$	
	public static final String t_c_report_emissions_weekly = Messages.getString("EMPSettings.50") + Messages.getString("EMPSettings.45") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.47") + Messages.getString("EMPSettings.11") + Messages.getString("EMPSettings.49"); //$NON-NLS-1$	
	
	public static final String report_prefix = Messages.getString("EMPSettings.45"); //$NON-NLS-1$
	public static final String power_prefix = Messages.getString("EMPSettings.46"); //$NON-NLS-1$
	public static final String emmissions_prefix = Messages.getString("EMPSettings.47"); //$NON-NLS-1$
	public static final String daily_prefix = Messages.getString("EMPSettings.48"); //$NON-NLS-1$
	public static final String weekly_prefix = Messages.getString("EMPSettings.49"); //$NON-NLS-1$

	public static final String path_to_report_private_folder = Messages.getString("EMPSettings.81"); //$NON-NLS-1$
	public static final String daily_path = Messages.getString("EMPSettings.82"); //$NON-NLS-1$
	public static final String weekly_path = Messages.getString("EMPSettings.83"); //$NON-NLS-1$
	public static final String energy_path = Messages.getString("EMPSettings.84"); //$NON-NLS-1$
	public static final String emissions_path = Messages.getString("EMPSettings.85"); //$NON-NLS-1$
	public static final String feeder_file_path = Messages.getString("EMPSettings.86"); //$NON-NLS-1$
	
	public static final String cron = Messages.getString("EMPSettings.87"); //$NON-NLS-1$
	public static final String birt_home = Messages.getString("EMPSettings.88"); //$NON-NLS-1$
	public static final String smp_photos = Messages.getString("EMPSettings.89"); //$NON-NLS-1$
	
	
	public static final String DB_TYPE_DATA = "data";
	public static final String DB_TYPE_UNIT = "unit";
	
	/**
	 * Storage Paths
	 */
	private static String excelStoragePath;

	public static String getExcelStoragePath(){
		return EMPSettings.excelStoragePath;
	}
	
	public static void setExcelStoragePath(String excelStoragePath){
		EMPSettings.excelStoragePath = excelStoragePath;
	}

	/**
	 * MESSAGE SETTINGS
	 */
	private static String parserMessage;
	
	public static String getParserMessage(){
		return EMPSettings.parserMessage;
	}
	
	public static void setParserMessage(String parserMessage){
		EMPSettings.parserMessage = parserMessage;
	}
	
	/**
	 * FACTORIES NAMES LIST
	 */
	private static ArrayList<String> ligniteFactories;
	private static ArrayList<String> ccgtFactories;
	private static ArrayList<String> hydroFactories;
	
	public static ArrayList<String> getLigniteFactories(){
		return EMPSettings.ligniteFactories;
	}
	
	public static void setLigniteFactories(ArrayList<String> ligniteFactories){
		EMPSettings.ligniteFactories = ligniteFactories;
	}
	
	public static ArrayList<String> getCcgtFactories(){
		return EMPSettings.ccgtFactories;
	}
	
	public static void setCcgtFactories(ArrayList<String> ccgtFactories){
		EMPSettings.ccgtFactories = ccgtFactories;
	}
	
	public static ArrayList<String> getHydroFactories(){
		return EMPSettings.hydroFactories;
	}
	
	public static void setHydroFactories(ArrayList<String> hydroFactories){
		EMPSettings.hydroFactories = hydroFactories;
	}
	
	/**
	 * DAILY REPORT
	 */
	private static int dailyReport_startHour;
	private static int dailyReport_startMinute;
	private static int dailyReport_endHour;
	private static int dailyReport_endMinute;
	
	public static int getDailyReport_startHour(){
		return EMPSettings.dailyReport_startHour;
	}
	
	public static void setDailyReport_startHour(int dailyReport_startHour){
		EMPSettings.dailyReport_startHour = dailyReport_startHour;
	}
	
	public static int getDailyReport_startMinute(){
		return EMPSettings.dailyReport_startMinute;
	}
	
	public static void setDailyReport_startMinute(int dailyReport_startMinute){
		EMPSettings.dailyReport_startMinute = dailyReport_startMinute;
	}
	
	public static int getDailyReport_endHour(){
		return EMPSettings.dailyReport_endHour;
	}
	
	public static void setDailyReport_endHour(int dailyReport_endHour){
		EMPSettings.dailyReport_endHour = dailyReport_endHour;
	}
	
	public static int getDailyReport_endMinute(){
		return EMPSettings.dailyReport_endMinute;
	}
	
	public static void setDailyReport_endMinute(int dailyReport_endMinute){
		EMPSettings.dailyReport_endMinute = dailyReport_endMinute;
	}
	
	/**
	 * WEEKLY REPORT
	 */
	private static int weeklyReport_startHour;
	private static int weeklyReport_startMinute;
	private static int weeklyReport_endHour;
	private static int weeklyReport_endMinute;
	
	public static int getWeeklyReport_startHour(){
		return EMPSettings.weeklyReport_startHour;
	}
	
	public static void setWeeklyReport_startHour(int weeklyReport_startHour){
		EMPSettings.weeklyReport_startHour = weeklyReport_startHour;
	}
	
	public static int getWeeklyReport_startMinute(){
		return EMPSettings.weeklyReport_startMinute;
	}
	
	public static void setWeeklyReport_startMinute(int weeklyReport_startMinute){
		EMPSettings.weeklyReport_startMinute = weeklyReport_startMinute;
	}
	
	public static int getWeeklyReport_endHour(){
		return EMPSettings.weeklyReport_endHour;
	}
	
	public static void setWeeklyReport_endHour(int weeklyReport_endHour){
		EMPSettings.weeklyReport_endHour = weeklyReport_endHour;
	}
	
	public static int getWeeklyReport_endMinute(){
		return EMPSettings.weeklyReport_endMinute;
	}
	
	public static void setWeeklyReport_endMinute(int weeklyReport_endMinute){
		EMPSettings.weeklyReport_endMinute = weeklyReport_endMinute;
	}
	
	/**
	 * DAS RESULTS FILE SETTINGS
	 * FILE NAMES 
	 */
	private static String DAS_R_1_1Suffix;
	private static String DAS_R_1_1URL;
	private static String DAS_R_1_1DaysAhead;
	private static String DAS_R_1_1DateFormat;
	
	public static String getDAS_R_Suffix(){
		return EMPSettings.DAS_R_1_1Suffix;
	}
	
	public static void setDAS_R_Suffix(String dayAheadSchedulingSuffix_1_1){
		EMPSettings.DAS_R_1_1Suffix = dayAheadSchedulingSuffix_1_1;
	}
	
	public static String getDAS_R_URL(){
		return EMPSettings.DAS_R_1_1URL;
	}
	
	public static void setDAS_R_URL(String dayAheadScheduling_1_1URL){
		EMPSettings.DAS_R_1_1URL = dayAheadScheduling_1_1URL;
	}
	
	public static String getDAS_R_DaysAhead(){
		return EMPSettings.DAS_R_1_1DaysAhead;
	}
	
	public static void setDAS_R_DaysAhead(String dayAheadScheduling_1_1DaysAhead){
		EMPSettings.DAS_R_1_1DaysAhead = dayAheadScheduling_1_1DaysAhead;
	}
	
	public static String getDAS_R_DateFormat(){
		return EMPSettings.DAS_R_1_1DateFormat;
	}
	
	public static void setDAS_R_DateFormat(String dayAheadScheduling_1_1DateFormat){
		EMPSettings.DAS_R_1_1DateFormat = dayAheadScheduling_1_1DateFormat;
	}
	
	/**
	 * TIME DATA
	 */
	private static int DAS_R_1_1StartHour;
	private static int DAS_R_1_1StartMinute;
	private static int DAS_R_1_1EndHour;
	private static int DAS_R_1_1EndMinute;
	
	public static int getDAS_R_1_1StartHour(){
		return EMPSettings.DAS_R_1_1StartHour;
	}
	
	public static void setDAS_R_1_1StartHour(int DAS_R_1_1StartHour){
		EMPSettings.DAS_R_1_1StartHour = DAS_R_1_1StartHour;
	}
	
	public static int getDAS_R_1_1StartMinute(){
		return EMPSettings.DAS_R_1_1StartMinute;
	}
	
	public static void setDAS_R_1_1StartMinute(int DAS_R_1_1StartMinute){
		EMPSettings.DAS_R_1_1StartMinute = DAS_R_1_1StartMinute;
	}
	
	public static int getDAS_R_1_1EndHour(){
		return EMPSettings.DAS_R_1_1EndHour;
	}
	
	public static void setDAS_R_1_1EndHour(int DAS_R_1_1EndHour){
		EMPSettings.DAS_R_1_1EndHour = DAS_R_1_1EndHour;
	}
	
	public static int getDAS_R_1_1EndMinute(){
		return EMPSettings.DAS_R_1_1EndMinute;
	}
	
	public static void setDAS_R_1_1EndMinute(int DAS_R_1_1EndMinute){
		EMPSettings.DAS_R_1_1EndMinute = DAS_R_1_1EndMinute;
	}
	
	/**
	 * SHEET NAMES
	 */
	//for 1.1 Day Ahead Scheduling Results
	private static String DAS_R_SheetSuffix;
	
	public static String getDasSheetSuffix(){
		return EMPSettings.DAS_R_SheetSuffix;
	}
	
	public static void setDAS_R_SheetSuffix(String dasSheetSuffix){
		EMPSettings.DAS_R_SheetSuffix = dasSheetSuffix;
	}
	
	/**
	 * ROW NAMES
	 */
	private static String DAS_R_loadDeclarationsRowName;
	private static String DAS_R_lossesRowName;
	private static String DAS_R_smpRowName;
	private static String DAS_R_renewablesRowName;
	private static String DAS_R_totalSchedules;
	
	public static String getDAS_R_LoadDeclarationsRowName(){
		return EMPSettings.DAS_R_loadDeclarationsRowName;
	}
	
	public static void setDAS_R_LoadDeclarationsRowName(String loadDeclarationsRowName){
		EMPSettings.DAS_R_loadDeclarationsRowName = loadDeclarationsRowName;
	}
	
	public static String getDAS_R_LossesRowName(){
		return EMPSettings.DAS_R_lossesRowName;
	}
	
	public static void setDAS_R_LossesRowName(String lossesRowName){
		EMPSettings.DAS_R_lossesRowName = lossesRowName;
	}
	
	public static String getDAS_R_SmpRowName(){
		return EMPSettings.DAS_R_smpRowName;
	}
	
	public static void setDAS_R_SmpRowName(String smpRowName){
		EMPSettings.DAS_R_smpRowName = smpRowName;
	}
	
	public static String getDAS_R_RenewablesRowName(){
		return EMPSettings.DAS_R_renewablesRowName;
	}
	
	public static void setDAS_R_RenewablesRowName(String renewablesRowName){
		EMPSettings.DAS_R_renewablesRowName = renewablesRowName;
	}
	
	public static String getDAS_R_totalSchedules(){
		return EMPSettings.DAS_R_totalSchedules;
	}
	
	public static void setDAS_R_totalSchedules(String DAS_R_totalSchedules){
		EMPSettings.DAS_R_totalSchedules = DAS_R_totalSchedules;
	}
	
	/**
	 * DAS UNIT AVAILABILITIES FILE SETTINGS
	 * FILE NAMES 
	 */
	private static String DAS_UA_1_3_FileSuffix;
	private static String DAS_UA_1_3_URL;
	private static String DAS_UA_1_3_DaysAhead;
	private static String DAS_UA_1_3_DateFormat;
	
	public static String getDAS_UA_FileSuffix(){
		return EMPSettings.DAS_UA_1_3_FileSuffix;
	}
	
	public static void setDAS_UA_FileSuffix(String DAS_UA_1_3_FileSuffix){
		EMPSettings.DAS_UA_1_3_FileSuffix = DAS_UA_1_3_FileSuffix;
	}
	
	public static String getDAS_UA_URL(){
		return EMPSettings.DAS_UA_1_3_URL;
	}
	
	public static void setDAS_UA_URL(String DAS_UA_1_3_URL){
		EMPSettings.DAS_UA_1_3_URL = DAS_UA_1_3_URL;
	}
	
	public static String getDAS_UA_DaysAhead(){
		return EMPSettings.DAS_UA_1_3_DaysAhead;
	}
	
	public static void setDAS_UA_DaysAhead(String DAS_UA_1_3_DaysAhead){
		EMPSettings.DAS_UA_1_3_DaysAhead = DAS_UA_1_3_DaysAhead;
	}
	
	public static String getDAS_UA_DateFormat(){
		return EMPSettings.DAS_UA_1_3_DateFormat;
	}
	
	public static void setDAS_UA_DateFormat(String DAS_UA_1_3_DateFormat){
		EMPSettings.DAS_UA_1_3_DateFormat = DAS_UA_1_3_DateFormat;
	}
	
	/**
	 * TIME DATA
	 */
	private static int DAS_UA_1_3StartHour;
	private static int DAS_UA_1_3StartMinute;
	private static int DAS_UA_1_3EndHour;
	private static int DAS_UA_1_3EndMinute;
	
	public static int getDAS_UA_1_3StartHour(){
		return EMPSettings.DAS_UA_1_3StartHour;
	}
	
	public static void setDAS_UA_1_3StartHour(int DAS_UA_1_3StartHour){
		EMPSettings.DAS_UA_1_3StartHour = DAS_UA_1_3StartHour;
	}
	
	public static int getDAS_UA_1_3StartMinute(){
		return EMPSettings.DAS_UA_1_3StartMinute;
	}
	
	public static void setDAS_UA_1_3StartMinute(int DAS_UA_1_3StartMinute){
		EMPSettings.DAS_UA_1_3StartMinute = DAS_UA_1_3StartMinute;
	}
	
	public static int getDAS_UA_1_3EndHour(){
		return EMPSettings.DAS_UA_1_3EndHour;
	}
	
	public static void setDAS_UA_1_3EndHour(int DAS_UA_1_3EndHour){
		EMPSettings.DAS_UA_1_3EndHour = DAS_UA_1_3EndHour;
	}
	
	public static int getDAS_UA_1_3EndMinute(){
		return EMPSettings.DAS_UA_1_3EndMinute;
	}
	
	public static void setDAS_UA_1_3EndMinute(int DAS_UA_1_3EndMinute){
		EMPSettings.DAS_UA_1_3EndMinute = DAS_UA_1_3EndMinute;
	}
	
	/**
	 * SHEET NAMES
	 */
	private static String DAS_UA_1_3_SheetName;
	
	public static String getDAS_UA_SheetName(){
		return EMPSettings.DAS_UA_1_3_SheetName;
	}
	
	public static void setDAS_UA_SheetName(String DAS_UA_1_3_SheetName){
		EMPSettings.DAS_UA_1_3_SheetName = DAS_UA_1_3_SheetName;
	}
	
	/**
	 * ROW NAMES
	 */
	private static String DAS_UA_1_3_rowTitle;
	
	public static String getDAS_UA_rowTitle(){
		return EMPSettings.DAS_UA_1_3_rowTitle;
	}
	
	public static void setDAS_UA_rowTitle(String DAS_UA_1_3_rowTitle){
		EMPSettings.DAS_UA_1_3_rowTitle = DAS_UA_1_3_rowTitle;
	}
	
	/**
	 * COLUMN NAMES
	 */
	private static String DAS_UA_1_3_colUnitName;
	private static String DAS_UA_1_3_colMaxNet;
	private static String DAS_UA_1_3_colEstimation;
	private static String DAS_UA_1_3_colComments;
	
	public static String getDAS_UA_colUnitName(){
		return EMPSettings.DAS_UA_1_3_colUnitName;
	}
	
	public static void setDAS_UA_colUnitName(String DAS_UA_1_3_colUnitName){
		EMPSettings.DAS_UA_1_3_colUnitName = DAS_UA_1_3_colUnitName;
	}
	
	public static String getDAS_UA_colMaxNet(){
		return EMPSettings.DAS_UA_1_3_colMaxNet;
	}
	
	public static void setDAS_UA_colMaxNet(String DAS_UA_1_3_colMaxNet){
		EMPSettings.DAS_UA_1_3_colMaxNet = DAS_UA_1_3_colMaxNet;
	}
	
	public static String getDAS_UA_colEstimation(){
		return EMPSettings.DAS_UA_1_3_colEstimation;
	}
	
	public static void setDAS_UA_colEstimation(String DAS_UA_1_3_colEstimation){
		EMPSettings.DAS_UA_1_3_colEstimation = DAS_UA_1_3_colEstimation;
	}
	
	public static String getDAS_UA_colComments(){
		return EMPSettings.DAS_UA_1_3_colComments;
	}
	
	public static void setDAS_UA_colComments(String DAS_UA_1_3_colComments){
		EMPSettings.DAS_UA_1_3_colComments = DAS_UA_1_3_colComments;
	}
	
	/**
	 * DAS WATER USAGE DECLARATIONS FILE SETTINGS
	 * FILE NAMES 
	 */
	private static String DAS_WU_1_4_Suffix;
	private static String DAS_WU_1_4_URL;
	private static String DAS_WU_1_4_DaysAhead;
	private static String DAS_WU_1_4_DateFormat;
	private static String DAS_WU_1_4_SheetName;
	
	public static String getDAS_WA_suffix(){
		return EMPSettings.DAS_WU_1_4_Suffix;
	}
	
	public static void setDAS_WA_suffix(String DAS_WU_1_4_Suffix){
		EMPSettings.DAS_WU_1_4_Suffix = DAS_WU_1_4_Suffix;
	}
	
	public static String getDAS_WA_URL(){
		return EMPSettings.DAS_WU_1_4_URL;
	}
	
	public static void setDAS_WA_URL(String DAS_WU_1_4_URL){
		EMPSettings.DAS_WU_1_4_URL = DAS_WU_1_4_URL;
	}
	
	public static String getDAS_WA_DaysAhead(){
		return EMPSettings.DAS_WU_1_4_DaysAhead;
	}
	
	public static void setDAS_WA_DaysAhead(String DAS_WU_1_4_DaysAhead){
		EMPSettings.DAS_WU_1_4_DaysAhead = DAS_WU_1_4_DaysAhead;
	}
	
	public static String getDAS_WA_DateFormat(){
		return EMPSettings.DAS_WU_1_4_DateFormat;
	}
	
	public static void setDAS_WA_DateFormat(String DAS_WU_1_4_DateFormat){
		EMPSettings.DAS_WU_1_4_DateFormat = DAS_WU_1_4_DateFormat;
	}
	
	public static String getDAS_WU_SheetName(){
		return EMPSettings.DAS_WU_1_4_SheetName;
	}
	
	public static void setDAS_WU_SheetName(String DAS_WU_SheetName){
		EMPSettings.DAS_WU_1_4_SheetName = DAS_WU_SheetName;
	}
	
	/**
	 * TIME DATA
	 */
	private static int DAS_WU_1_4_StartHour;
	private static int DAS_WU_1_4_StartMinute;
	private static int DAS_WU_1_4_EndHour;
	private static int DAS_WU_1_4_EndMinute;
	
	public static int getDAS_WU_1_4_StartHour(){
		return EMPSettings.DAS_WU_1_4_StartHour;
	}
	
	public static void setDAS_WU_1_4_StartHour(int DAS_WU_1_4_StartHour){
		EMPSettings.DAS_WU_1_4_StartHour = DAS_WU_1_4_StartHour;
	}
	
	public static int getDAS_WU_1_4_StartMinute(){
		return EMPSettings.DAS_WU_1_4_StartMinute;
	}
	
	public static void setDAS_WU_1_4_StartMinute(int DAS_WU_1_4_StartMinute){
		EMPSettings.DAS_WU_1_4_StartMinute = DAS_WU_1_4_StartMinute;
	}
	
	public static int getDAS_WU_1_4_EndHour(){
		return EMPSettings.DAS_WU_1_4_EndHour;
	}
	
	public static void setDAS_WU_1_4_EndHour(int DAS_WU_1_4_EndHour){
		EMPSettings.DAS_WU_1_4_EndHour = DAS_WU_1_4_EndHour;
	}
	
	public static int getDAS_WU_1_4_EndMinute(){
		return EMPSettings.DAS_WU_1_4_EndMinute;
	}
	
	public static void setDAS_WU_1_4_EndMinute(int DAS_WU_1_4_EndMinute){
		EMPSettings.DAS_WU_1_4_EndMinute = DAS_WU_1_4_EndMinute;
	}
	
	private static String DAS_WU_rowTitle;
	private static String DAS_WU_totalRowName;
	private static String DAS_WU_totalColName;
	
	public static String getDAS_WU_rowTitle(){
		return EMPSettings.DAS_WU_rowTitle;
	}
	
	public static void setDAS_WU_rowTitle(String DAS_WA_rowTitle){
		EMPSettings.DAS_WU_rowTitle = DAS_WA_rowTitle;
	}
	
	public static String getDAS_WU_totalRowName(){
		return EMPSettings.DAS_WU_totalRowName;
	}
	
	public static void setDAS_WU_totalRowName(String DAS_WA_totalRowName){
		EMPSettings.DAS_WU_totalRowName = DAS_WA_totalRowName;
	}
	
	public static String getDAS_WU_totalColName(){
		return EMPSettings.DAS_WU_totalColName;
	}
	
	public static void setDAS_WU_totalColName(String DAS_WA_totalColName){
		EMPSettings.DAS_WU_totalColName = DAS_WA_totalColName;
	}
	
	/**
	 * DAS LOAD FORECAST FILE SETTINGS
	 * FILE NAMES 
	 */
	private static String DAS_LF_1_5_Suffix;
	private static String DAS_LF_1_5_URL;
	private static String DAS_LF_1_5_DaysAhead;
	private static String DAS_LF_1_5_DateFormat;
	private static String DAS_LF_1_5_SheetName;
	
	public static String getDAS_LF_1_5_URL(){
		return EMPSettings.DAS_LF_1_5_URL;
	}
	
	public static void setDAS_LF_1_5_URL(String DAS_LF_1_5_URL){
		EMPSettings.DAS_LF_1_5_URL = DAS_LF_1_5_URL;
	}
	
	public static String getDAS_LF_1_5_Suffix(){
		return EMPSettings.DAS_LF_1_5_Suffix;
	}
	
	public static void setDAS_LF_1_5_Suffix(String DAS_LF_1_5_Suffix){
		EMPSettings.DAS_LF_1_5_Suffix = DAS_LF_1_5_Suffix;
	}
	
	public static String getDAS_LF_1_5_DaysAhead(){
		return EMPSettings.DAS_LF_1_5_DaysAhead;
	}
	
	public static void setDAS_LF_1_5_DaysAhead(String DAS_LF_1_5_DaysAhead){
		EMPSettings.DAS_LF_1_5_DaysAhead = DAS_LF_1_5_DaysAhead;
	}
	
	public static String getDAS_LF_1_5_DateFormat(){
		return EMPSettings.DAS_LF_1_5_DateFormat;
	}
	
	public static void setDAS_LF_1_5_DateFormat(String DAS_LF_1_5_DateFormat){
		EMPSettings.DAS_LF_1_5_DateFormat = DAS_LF_1_5_DateFormat;
	}
	
	public static String getDAS_LF_1_5_SheetName(){
		return EMPSettings.DAS_LF_1_5_SheetName;
	}
	
	public static void setDAS_LF_1_5_SheetName(String DAS_LF_1_5_SheetName){
		EMPSettings.DAS_LF_1_5_SheetName = DAS_LF_1_5_SheetName;
	}
	
	private static String DAS_LF_1_5_TitlesRowTitle;
	
	public static String getDAS_LF_1_5_TitlesRowTitle(){
		return EMPSettings.DAS_LF_1_5_TitlesRowTitle;
	}
	
	public static void setDAS_LF_1_5_TitlesRowTitle(String DAS_LF_1_5_TitlesRowTitle){
		EMPSettings.DAS_LF_1_5_TitlesRowTitle = DAS_LF_1_5_TitlesRowTitle;
	}
	
	/**
	 * TIME DATA
	 */
	private static int DAS_LF_1_5_StartHour;
	private static int DAS_LF_1_5_StartMinute;
	private static int DAS_LF_1_5_EndHour;
	private static int DAS_LF_1_5_EndMinute;
	
	public static int getDAS_LF_1_5_StartHour(){
		return EMPSettings.DAS_LF_1_5_StartHour;
	}
	
	public static void setDAS_LF_1_5_StartHour(int DAS_LF_1_5_StartHour){
		EMPSettings.DAS_LF_1_5_StartHour = DAS_LF_1_5_StartHour;
	}
	
	public static int getDAS_LF_1_5_StartMinute(){
		return EMPSettings.DAS_LF_1_5_StartMinute;
	}
	
	public static void setDAS_LF_1_5_StartMinute(int DAS_LF_1_5_StartMinute){
		EMPSettings.DAS_LF_1_5_StartMinute = DAS_LF_1_5_StartMinute;
	}
	
	public static int getDAS_LF_1_5_EndHour(){
		return EMPSettings.DAS_LF_1_5_EndHour;
	}
	
	public static void setDAS_LF_1_5_EndHour(int DAS_LF_1_5_EndHour){
		EMPSettings.DAS_LF_1_5_EndHour = DAS_LF_1_5_EndHour;
	}
	
	public static int getDAS_LF_1_5_EndMinute(){
		return EMPSettings.DAS_LF_1_5_EndMinute;
	}
	
	public static void setDAS_LF_1_5_EndMinute(int DAS_LF_1_5_EndMinute){
		EMPSettings.DAS_LF_1_5_EndMinute = DAS_LF_1_5_EndMinute;
	}
	
	/**
	 * WEATHER FORECAST
	 */
	private static String WeatherForecast_URL;
	private static String WeatherForecast_daysAhead;
	private static String WeatherForecast_suffix;
	
	public static void setWeatherForecast_URL(String WeatherForecast_URL){
		EMPSettings.WeatherForecast_URL = WeatherForecast_URL;
	}
	
	public static String getWeatherForecast_URL(){
		return EMPSettings.WeatherForecast_URL;
	}
	
	public static void setWeatherForecast_daysAhead(String WeatherForecast_daysAhead){
		EMPSettings.WeatherForecast_daysAhead = WeatherForecast_daysAhead;
	}
	
	public static String getWeatherForecast_daysAhead(){
		return EMPSettings.WeatherForecast_daysAhead;
	}
	
	public static void setWeatherForecast_suffix(String WeatherForecast_suffix){
		EMPSettings.WeatherForecast_suffix = WeatherForecast_suffix;
	}
	
	public static String getWeatherForecast_suffix(){
		return EMPSettings.WeatherForecast_suffix;
	}
	
	/**
	 * TIME DATA
	 */
	private static int WeatherForecast_StartHour;
	private static int WeatherForecast_StartMinute;
	private static int WeatherForecast_EndHour;
	private static int WeatherForecast_EndMinute;
	
	public static int getWeatherForecast_StartHour(){
		return EMPSettings.WeatherForecast_StartHour;
	}
	
	public static void setWeatherForecast_StartHour(int WeatherForecast_StartHour){
		EMPSettings.WeatherForecast_StartHour = WeatherForecast_StartHour;
	}
	
	public static int getWeatherForecast_StartMinute(){
		return EMPSettings.WeatherForecast_StartMinute;
	}
	
	public static void setWeatherForecast_StartMinute(int WeatherForecast_StartMinute){
		EMPSettings.WeatherForecast_StartMinute = WeatherForecast_StartMinute;
	}
	
	public static int getWeatherForecast_EndHour(){
		return EMPSettings.WeatherForecast_EndHour;
	}
	
	public static void setWeatherForecast_EndHour(int WeatherForecast_EndHour){
		EMPSettings.WeatherForecast_EndHour = WeatherForecast_EndHour;
	}
	
	public static int getWeatherForecast_EndMinute(){
		return EMPSettings.WeatherForecast_EndMinute;
	}
	
	public static void setWeatherForecast_EndMinute(int WeatherForecast_EndMinute){
		EMPSettings.WeatherForecast_EndMinute = WeatherForecast_EndMinute;
	}
	
	/**
	 * BNS STATS FILE SETTINGS
	 * FILE NAMES 
	 */
	private static String BNS_STATS_3_1_FileSuffix;
	private static String BNS_STATS_3_1_URL;
	private static String BNS_STATS_3_1_DaysAhead;
	private static String BNS_STATS_3_1_DateFormat;
	
	public static String getBNS_STATS_3_1_FileSuffix(){
		return EMPSettings.BNS_STATS_3_1_FileSuffix;
	}
	
	public static void setBNS_STATS_3_1_FileSuffix(String BNS_STATS_3_1_FileSuffix){
		EMPSettings.BNS_STATS_3_1_FileSuffix = BNS_STATS_3_1_FileSuffix;
	}
	
	public static String getBNS_STATS_3_1_URL(){
		return EMPSettings.BNS_STATS_3_1_URL;
	}
	
	public static void setBNS_STATS_3_1_URL(String BNS_STATS_3_1_URL){
		EMPSettings.BNS_STATS_3_1_URL = BNS_STATS_3_1_URL;
	}
	
	public static String getBNS_STATS_3_1_DaysAhead(){
		return EMPSettings.BNS_STATS_3_1_DaysAhead;
	}
	
	public static void setBNS_STATS_3_1_DaysAhead(String BNS_STATS_3_1_DaysAhead){
		EMPSettings.BNS_STATS_3_1_DaysAhead = BNS_STATS_3_1_DaysAhead;
	}
	
	public static String getBNS_STATS_3_1_DateFormat(){
		return EMPSettings.BNS_STATS_3_1_DateFormat;
	}
	
	public static void setBNS_STATS_3_1_DateFormat(String BNS_STATS_3_1_DateFormat){
		EMPSettings.BNS_STATS_3_1_DateFormat = BNS_STATS_3_1_DateFormat;
	}
	
	/**
	 * TIME DATA
	 */
	private static int BNS_STATS_3_1_StartHour;
	private static int BNS_STATS_3_1_StartMinute;
	private static int BNS_STATS_3_1_EndHour;
	private static int BNS_STATS_3_1_EndMinute;
	
	public static int getBNS_STATS_3_1_StartHour(){
		return EMPSettings.BNS_STATS_3_1_StartHour;
	}
	
	public static void setBNS_STATS_3_1_StartHour(int BNS_STATS_3_1_StartHour){
		EMPSettings.BNS_STATS_3_1_StartHour = BNS_STATS_3_1_StartHour;
	}
	
	public static int getBNS_STATS_3_1_StartMinute(){
		return EMPSettings.BNS_STATS_3_1_StartMinute;
	}
	
	public static void setBNS_STATS_3_1_StartMinute(int BNS_STATS_3_1_StartMinute){
		EMPSettings.BNS_STATS_3_1_StartMinute = BNS_STATS_3_1_StartMinute;
	}
	
	public static int getBNS_STATS_3_1_EndHour(){
		return EMPSettings.BNS_STATS_3_1_EndHour;
	}
	
	public static void setBNS_STATS_3_1_EndHour(int BNS_STATS_3_1_EndHour){
		EMPSettings.BNS_STATS_3_1_EndHour = BNS_STATS_3_1_EndHour;
	}
	
	public static int getBNS_STATS_3_1_EndMinute(){
		return EMPSettings.BNS_STATS_3_1_EndMinute;
	}
	
	public static void setBNS_STATS_3_1_EndMinute(int BNS_STATS_3_1_EndMinute){
		EMPSettings.BNS_STATS_3_1_EndMinute = BNS_STATS_3_1_EndMinute;
	}
	
	
	private static String BNS_STATS_3_1_sheet_name;
	private static int BNS_STATS_3_1_column_titles_row_index;
	private static String BNS_STATS_3_1_date_column_name;
	private static String BNS_STATS_3_1_product_column_name;
	private static String BNS_STATS_3_1_daily_closing_price_column_name;
	private static String BNS_STATS_3_1_variation_column_name;
	private static String BNS_STATS_3_1_listed_volume_column_name;
	private static String BNS_STATS_3_1_otc_volume_column_name;
	private static String BNS_STATS_3_1_total_volume_column_name;
	
	public static String getBNS_STATS_3_1_sheet_name(){
		return EMPSettings.BNS_STATS_3_1_sheet_name;
	}
	
	public static void setBNS_STATS_3_1_sheet_name(String BNS_STATS_3_1_sheet_name){
		EMPSettings.BNS_STATS_3_1_sheet_name = BNS_STATS_3_1_sheet_name;
	}
	
	public static int getBNS_STATS_3_1_column_titles_row_index(){
		return EMPSettings.BNS_STATS_3_1_column_titles_row_index;
	}
	
	public static void setBNS_STATS_3_1_column_titles_row_index(int BNS_STATS_3_1_column_titles_row_index){
		EMPSettings.BNS_STATS_3_1_column_titles_row_index = BNS_STATS_3_1_column_titles_row_index;
	}
	
	public static String getBNS_STATS_3_1_date_column_name(){
		return EMPSettings.BNS_STATS_3_1_date_column_name;
	}
	
	public static void setBNS_STATS_3_1_date_column_name(String BNS_STATS_3_1_date_column_name){
		EMPSettings.BNS_STATS_3_1_date_column_name = BNS_STATS_3_1_date_column_name;
	}
	
	public static String getBNS_STATS_3_1_product_column_name(){
		return EMPSettings.BNS_STATS_3_1_product_column_name;
	}
	
	public static void setBNS_STATS_3_1_product_column_name(String BNS_STATS_3_1_product_column_name){
		EMPSettings.BNS_STATS_3_1_product_column_name = BNS_STATS_3_1_product_column_name;
	}
	
	public static String getBNS_STATS_3_1_daily_closing_price_column_name(){
		return EMPSettings.BNS_STATS_3_1_daily_closing_price_column_name;
	}
	
	public static void setBNS_STATS_3_1_daily_closing_price_column_name(String BNS_STATS_3_1_daily_closing_price_column_name){
		EMPSettings.BNS_STATS_3_1_daily_closing_price_column_name = BNS_STATS_3_1_daily_closing_price_column_name;
	}
	
	public static String getBNS_STATS_3_1_variation_column_name(){
		return EMPSettings.BNS_STATS_3_1_variation_column_name;
	}
	
	public static void setBNS_STATS_3_1_variation_column_name(String BNS_STATS_3_1_variation_column_name){
		EMPSettings.BNS_STATS_3_1_variation_column_name = BNS_STATS_3_1_variation_column_name;
	}
	
	public static String getBNS_STATS_3_1_listed_volume_column_name(){
		return EMPSettings.BNS_STATS_3_1_listed_volume_column_name;
	}
	
	public static void setBNS_STATS_3_1_listed_volume_column_name(String BNS_STATS_3_1_listed_volume_column_name){
		EMPSettings.BNS_STATS_3_1_listed_volume_column_name = BNS_STATS_3_1_listed_volume_column_name;
	}
	
	public static String getBNS_STATS_3_1_otc_volume_column_name(){
		return EMPSettings.BNS_STATS_3_1_otc_volume_column_name;
	}
	
	public static void setBNS_STATS_3_1_otc_volume_column_name(String BNS_STATS_3_1_otc_volume_column_name){
		EMPSettings.BNS_STATS_3_1_otc_volume_column_name = BNS_STATS_3_1_otc_volume_column_name;
	}
	
	public static String getBNS_STATS_3_1_total_volume_column_name(){
		return EMPSettings.BNS_STATS_3_1_total_volume_column_name;
	}
	
	public static void setBNS_STATS_3_1_total_volume_column_name(String BNS_STATS_3_1_total_volume_column_name){
		EMPSettings.BNS_STATS_3_1_total_volume_column_name = BNS_STATS_3_1_total_volume_column_name;
	}
	
	private static String BNS_STATS_3_1_BNS_CER;
	private static String BNS_STATS_3_1_BNS_CER_LH;
	private static String BNS_STATS_3_1_Spread_outright_BNS_CER_EUA;
	private static String BNS_STATS_3_1_BNS_ERU;
	private static String BNS_STATS_3_1_BNS_ERU_LH;
	private static String BNS_STATS_3_1_BNS_EUA_08_12;
	private static String BNS_STATS_3_1_BNS_EUAA;
	private static String BNS_STATS_3_1_BNS_GREEN_CER;
	
	public static String getBNS_STATS_3_1_BNS_CER(){
		return EMPSettings.BNS_STATS_3_1_BNS_CER;
	}
	
	public static void setBNS_STATS_3_1_BNS_CER(String BNS_STATS_3_1_BNS_CER){
		EMPSettings.BNS_STATS_3_1_BNS_CER = BNS_STATS_3_1_BNS_CER;
	}
	
	public static String getBNS_STATS_3_1_BNS_CER_LH(){
		return EMPSettings.BNS_STATS_3_1_BNS_CER_LH;
	}
	
	public static void setBNS_STATS_3_1_BNS_CER_LH(String BNS_STATS_3_1_BNS_CER_LH){
		EMPSettings.BNS_STATS_3_1_BNS_CER_LH = BNS_STATS_3_1_BNS_CER_LH;
	}
	
	public static String getBNS_STATS_3_1_Spread_outright_BNS_CER_EUA(){
		return EMPSettings.BNS_STATS_3_1_Spread_outright_BNS_CER_EUA;
	}
	
	public static void setBNS_STATS_3_1_Spread_outright_BNS_CER_EUA(String BNS_STATS_3_1_Spread_outright_BNS_CER_EUA){
		EMPSettings.BNS_STATS_3_1_Spread_outright_BNS_CER_EUA = BNS_STATS_3_1_Spread_outright_BNS_CER_EUA;
	}
	
	public static String getBNS_STATS_3_1_BNS_ERU(){
		return EMPSettings.BNS_STATS_3_1_BNS_ERU;
	}
	
	public static void setBNS_STATS_3_1_BNS_ERU(String BNS_STATS_3_1_BNS_ERU){
		EMPSettings.BNS_STATS_3_1_BNS_ERU = BNS_STATS_3_1_BNS_ERU;
	}
	
	public static String getBNS_STATS_3_1_BNS_ERU_LH(){
		return EMPSettings.BNS_STATS_3_1_BNS_ERU_LH;
	}
	
	public static void setBNS_STATS_3_1_BNS_ERU_LH(String BNS_STATS_3_1_BNS_ERU_LH){
		EMPSettings.BNS_STATS_3_1_BNS_ERU_LH = BNS_STATS_3_1_BNS_ERU_LH;
	}
	
	public static String getBNS_STATS_3_1_BNS_EUA_08_12(){
		return EMPSettings.BNS_STATS_3_1_BNS_EUA_08_12;
	}
	
	public static void setBNS_STATS_3_1_BNS_EUA_08_12(String BNS_STATS_3_1_BNS_EUA_08_12){
		EMPSettings.BNS_STATS_3_1_BNS_EUA_08_12 = BNS_STATS_3_1_BNS_EUA_08_12;
	}
	
	public static String getBNS_STATS_3_1_BNS_EUAA(){
		return EMPSettings.BNS_STATS_3_1_BNS_EUAA;
	}
	
	public static void setBNS_STATS_3_1_BNS_EUAA(String BNS_STATS_3_1_BNS_EUAA){
		EMPSettings.BNS_STATS_3_1_BNS_EUAA = BNS_STATS_3_1_BNS_EUAA;
	}
	
	public static String getBNS_STATS_3_1_BNS_GREEN_CER(){
		return EMPSettings.BNS_STATS_3_1_BNS_GREEN_CER;
	}
	
	public static void setBNS_STATS_3_1_BNS_GREEN_CER(String BNS_STATS_3_1_BNS_GREEN_CER){
		EMPSettings.BNS_STATS_3_1_BNS_GREEN_CER = BNS_STATS_3_1_BNS_GREEN_CER;
	}

}
