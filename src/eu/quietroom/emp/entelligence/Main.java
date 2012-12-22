package eu.quietroom.emp.entelligence;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.quietroom.emp.entelligence.dbaccess.DBTools;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataEntity;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.entelligence.dbaccess.LogEntity;
import eu.quietroom.emp.entelligence.dbaccess.LogRowEntity;
import eu.quietroom.emp.entelligence.dbaccess.UnitEntity;
import eu.quietroom.emp.entelligence.dbaccess.UnitRowEntity;
import eu.quietroom.emp.entelligence.parsers.BNS_STATS_parser;
import eu.quietroom.emp.entelligence.parsers.DAS_R_Parser;
import eu.quietroom.emp.entelligence.parsers.DAS_UA_Parser;
import eu.quietroom.emp.entelligence.parsers.SettingsParser;
import eu.quietroom.emp.entelligence.parsers.WA_LF_Parser;
import eu.quietroom.emp.entelligence.parsers.WA_WUD_Parser;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.downloadUtils.DownloadUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class Main {
    public static void main(String[] args){
//    	dateTests();
//    	tests();
//    	test2();
//    	test3();
    	
//    	test4();
    	
//    	persistInformation();
//    	downloadFilesLoop(1,10);
//    	fillDB(1,10);
//    	testWUParser();
//    	testLFParser();
//    	testXML();
    	
//    	testJSoup();
//		String url = "http://www.lagie.gr/fileadmin/user_upload/reports/DayAheadSchedulingResults/20120725_DayAheadSchedulingResults_01.xls";
//		DownlloadUtils.downloadFile(url, "td.xls");
//		ExcelUtils.readExcel("files/excels/20120715_DayAheadSchedulingResults_01.xls");
//		Workbook wbs = ExcelUtils.getExcel("files/excels/20120715_DayAheadSchedulingResults_01.xls");
//		Workbook wbs = ExcelUtils.getExcel("files/excels/1.1_20120726_DayAheadSchedulingResults_01.xls");
//		System.out.println((ExcelUtils.getValues(wbs, "20120726_DAS", "LOAD DECLARATIONS + LOSSES")).toString());
//		System.out.println((ExcelUtils.getValues(wbs, "20120726_DAS", "SMP")).toString());
//		ExcelUtils.readExcel("http://www.lagie.gr/fileadmin/user_upload/reports/DayAheadSchedulingResults/20120725_DayAheadSchedulingResults_01.xls");
//    	testLog();
    	
    	/**
    	 * FOR AC
    	 */
//    	downloadFilesLoop(1,10); 		//me autin tin function katevazei ta arxeia apo daysFromToday apo to tora kai gia diastima numOfDays, 
    									//downloadFilesLoop(1,10) edo dld katevazei apo 20120901 mexri 20120822 (tin etreksa stis 20120902)
    									//to dokimasa me pio megalo diastima kai ena arxeio den iparxei gia kapoio gamimeno logo
    									//opos kai na exei den tha tin xrisimopoiiseis mallon afou tha anevaso ta arxeia pou katevikan
    	
//    	fillDB(1,10);					//me autin tin function vazei ta data sti vasi arkei na iparxoun ta arxeia (ta orismata einai idia me tin downloadFilesLoop)
    									//tora iparxei ena bug otan pao na eisago ta UnitRowEntities, pros to paron to exo kanei sxolio sti function persistInformationByDate (se dio simeia, ta exo sxoliasei kai mesa sti function)

//    	testBNS_STATS_parser();
//    	System.out.println("TEST TEST");
//    	System.out.println(EMPSettings.t_c_report_power_daily);
//    	System.out.println(EMPSettings.t_c_report_power_weekly);
//    	System.out.println(EMPSettings.t_c_report_emissions_weekly);
//    	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//    	String date = myFormat.format(new Date());
//    	BirtIntegration.createReport(BirtIntegration.EMP_DAILY_POWER, "2012-11-14");

//    	System.out.println(DateUtils.convertToString(DateUtils.getDateByDay(1), "yyyy-MM-dd"));
//    	System.out.println(EMPSettings.dbDailyPower_top);
//    	System.out.println(EMPSettings.t_p_daily_power_customValues);
    	
    	runScheduler();
    }
    
    public static void runScheduler(){
    	EMPScheduler empScheduler = new EMPScheduler("files/system/settings.xlsx", 1000*60*2);
    	Thread t = new Thread(empScheduler);
    	t.start();
    }
    
    public static void testBNS_STATS_parser(){
    	System.out.println("testBNS_STATS_parser");
    	SettingsParser ps = new SettingsParser();
    	ps.setParser("files/system/settings.xlsx", "");
    	ps.gatherData();
    	
    	BNS_STATS_parser bns = new BNS_STATS_parser();
    	bns.setParser("files/excels/20121004_BNS_STATS.xls", "20121004");
    	bns.gatherData();
    }
    
    public static ArrayList<Integer> getMaxTemperatures(Document doc){
    	return getTemperatures(doc, 0);
    }
    
    public static ArrayList<Integer> getMinTemperatures(Document doc){
    	return getTemperatures(doc, 1);
    }
    
    public static ArrayList<Integer> getTemperatures(Document doc, int type){
    	ArrayList<Integer> out = new ArrayList<Integer>();
    	Elements elList = doc.select("#forecast .fctDay .fctHiLow");
    	for(Element el : elList){
			String s = el.text();
			s = s.substring(0, s.length() - 3);
			String[] arr = s.split(" \\| ");
			if(type < arr.length){
				out.add(Integer.parseInt(arr[type]));
			}
		}
    	return out;
    }
    
    public static ArrayList<Double> getWindSpeed(Document doc){
    	ArrayList<Double> out = new ArrayList<Double>();
    	int daysCounter = 0;
    	Elements elList = doc.select("#forecast #fct_details .clearItem tr");
    	for(Element el : elList){
    		Elements rows = el.select("td");
    		double wSpeed;
    		if(rows.size() > 0 && "Wind".equals(rows.get(0).text())){
    			daysCounter++;
    			double counter = 0.0;
    			wSpeed = 0.0;
    			for(Element row : rows){
    				if(!"Wind".equals(row.text())){
    					String s = row.text();
    					String[] sList = s.split(" km");
    					wSpeed += Double.parseDouble(sList[0]);
    					counter++;
    				}
        		}
    			wSpeed = wSpeed/counter;
    			out.add(new Double(wSpeed));
    		}
    		if(daysCounter > 6){
    			break;
    		}
		}
    	return out;
    }
    
    public static ArrayList<Double> getPrecipitationChance(Document doc){
    	ArrayList<Double> out = new ArrayList<Double>();
    	Elements elList = doc.select("#forecast #fct_details .clearItem tr");
    	int daysCounter = 0;
    	for(Element el : elList){
    		Elements rows = el.select("td");
    		double wSpeed;
    		if(rows.size() > 0 && "Chance of Precip.".equals(rows.get(0).text())){
    			daysCounter++;
    			double counter = 0.0;
    			wSpeed = 0.0;
    			for(Element row : rows){
    				if(!"Chance of Precip.".equals(row.text())){
    					String s = row.text();
    					wSpeed += Double.parseDouble(s.substring(0, s.length() - 1));
    					counter++;
    				}
        		}
    			wSpeed = wSpeed/counter;
    			out.add(new Double(wSpeed));
    		}
    		if(daysCounter > 6){
    			break;
    		}
		}
    	return out;
    }
    
    public static ArrayList<Integer> getCloudCover(Document doc){
    	ArrayList<Integer> out = new ArrayList<Integer>();
    	Elements elList = doc.select("#forecast #fct_details .clearItem tr");
    	int daysCounter = 0;
    	for(Element el : elList){
    		Elements rows = el.select("td");
    		double sunnyHours;
    		if(rows.size() > 0 && "Cloud Cover".equals(rows.get(0).text())){
    			daysCounter++;
    			double counter = 0.0;
    			sunnyHours = 0.0;
    			for(Element row : rows){
    				if(!"Cloud Cover".equals(row.text())){
    					String s = row.text();
    					counter++;
    					if(counter%4 == 1){
    						sunnyHours += (2.0/12.0)*(100.0 - Double.parseDouble(s.substring(0, s.length() - 1)));
    					}else if(counter%4 == 2){
    						sunnyHours += (6.0/12.0)*(100.0 - Double.parseDouble(s.substring(0, s.length() - 1)));
    					}else if(counter%4 == 3){
    						sunnyHours += (4.0/12.0)*(100.0 - Double.parseDouble(s.substring(0, s.length() - 1)));
    					}
    				}
        		}
    			out.add(new Integer((int)Math.round((sunnyHours/100.0)*12.0)));
    		}
    		if(daysCounter > 6){
    			break;
    		}
		}
    	return out;
    }
    
    public static void testJSoup(){
//    	DownloadUtils.downloadFile("http://free.worldweatheronline.com/feed/weather.ashx?q=Athens,Greece&format=xml&num_of_days=5&key=738a9b269b105033120209", "files/weather.xml");
    	String filePath = "files/weather.html";
    	String url = "http://www.wunderground.com/global/stations/16701.html";
    	File input = new File("files/excels/weatherTest.html");
//    	File input = new File("files/weather.html");
//    	DownloadUtils.downloadFile(url, filePath);
    	try {
    		Document doc = Jsoup.parse(input, "UTF-8", url);
    		System.out.println("MAX TEMPERATURES: " + getMaxTemperatures(doc));
    		System.out.println("MIN TEMPERATURES: " + getMinTemperatures(doc));
    		System.out.println("WIND SPEED: " + getWindSpeed(doc));
    		System.out.println("CHANCE OF PRECIPITATION: " + getPrecipitationChance(doc));
    		System.out.println("SUNNY HOURS: " + getCloudCover(doc));
//    		Elements elList = doc.select("#forecast .fctDay .fctHiLow span.b");
//    		Elements elList = doc.select("#forecast .fctDay .fctHiLow");
//    		Elements elList = doc.select("#forecast .fctDay");
//    		Elements elList = doc.select("#forecast .fct_hourlytable");
//    		for(Element el : elList){
//    			String s = el.text();
//    			s = s.substring(0, s.length() - 3);
//    			System.out.println(s);
//    			String[] arr = s.split(" \\| ");
//    			for(int i = 0; i < arr.length; i++){
//    				System.out.println("### " + arr[i]);
//    			}
//    		}
//    		System.out.println(elList.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
//    	try {
//			Document doc = Jsoup.connect("http://www.wunderground.com/global/stations/16701.html").get();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }
    
    public static void testXML(){
    	DownloadUtils.downloadFile("http://free.worldweatheronline.com/feed/weather.ashx?q=Athens,Greece&format=xml&num_of_days=5&key=738a9b269b105033120209", "files/weather.xml");
    	DownloadUtils.downloadFile("http://www.wunderground.com/global/stations/16701.html", "files/weather.html");
//    	System.out.println(CrawlerUtils.getString("http://www.wunderground.com/global/stations/16701.html"));
    }
    
    public static IndexRange getHoursRange(String fileName){
    	Workbook wbs = ExcelUtils.getExcel(fileName);
    	ExcelUtils.getValuesByRowName(wbs, "20120726_DAS", "UNIT / HOUR");
    	
    	return null;
    }
    
    public static void dateTests(){
//    	DateUtils.test();
    	System.out.println(DateUtils.convertToString(DateUtils.getDateByDay(-4), "yyyyMMdd"));
//    	Date d = new Date();
//    	Calendar calendar = Calendar.getInstance();
//    	System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//    	System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//    	System.out.println("DAY_OF_MONTH: " + calendar.toString());
    	
    }
    
    public static void test2(){
    	SettingsParser ps = new SettingsParser();
    	ps.setParser("files/system/settings.xlsx", "");
    	ps.gatherData();
    	
    	String dateString = DateUtils.convertToString(DateUtils.getDateByDay(0), "yyyyMMdd");
    	
    	String DAS_R_fname = dateString + EMPSettings.getDAS_R_Suffix();
    	String DAS_R_url = EMPSettings.getDAS_R_URL() + DAS_R_fname;
		DownloadUtils.downloadFile(DAS_R_url, EMPSettings.getExcelStoragePath() + DAS_R_fname);
		printDAS_R_Data(EMPSettings.getExcelStoragePath() + DAS_R_fname, dateString);
		
		String DAS_UA_fname = dateString + EMPSettings.getDAS_UA_FileSuffix();
    	String DAS_UA_url = EMPSettings.getDAS_UA_URL() + DAS_UA_fname;
		DownloadUtils.downloadFile(DAS_UA_url, EMPSettings.getExcelStoragePath() + DAS_UA_fname);
		printDAS_UA_Data(EMPSettings.getExcelStoragePath() + DAS_UA_fname, dateString);
    }
    
    public static void printDAS_R_Data(String dasFileName, String dateString){
    	DAS_R_Parser das_r = new DAS_R_Parser();
    	das_r.setParser(dasFileName, dateString);
    	
    	System.out.println();
    	System.out.println("LOAD DECLARATIONS: " + das_r.getLoadDeclarationsValues().toString());
    	System.out.println("LOSSES: " + das_r.getLossesValues().toString());
    	System.out.println("SMP: " + das_r.getSMPValues().toString());
    	
    	System.out.println();
    	System.out.println("LIGNITE");
    	for(String s : EMPSettings.getLigniteFactories()){
    		System.out.println(s + ": " + das_r.getHourValuesByRow(s));    		
    	}
    	
    	System.out.println();
    	System.out.println("CCGT");
    	for(String s : EMPSettings.getCcgtFactories()){
    		System.out.println(s + ": " + das_r.getHourValuesByRow(s));    		
    	}
    	
    	System.out.println();
    	System.out.println("HYDRO");
    	for(String s : EMPSettings.getHydroFactories()){
    		System.out.println(s + ": " + das_r.getHourValuesByRow(s));    		
    	}
    }
    
    public static void printDAS_UA_Data(String dasFileName, String dateString){
    	DAS_UA_Parser das_ua = new DAS_UA_Parser();
    	das_ua.setParser(dasFileName, "");
    	das_ua.gatherData();
    	
    	System.out.println("\nFACTORIES WITH COMMENTS:\n");
    	for(String factory : das_ua.getFactoriesWithComments()){
    		System.out.println(factory + ", MAX NET: " + das_ua.getMaxNetByFactory(factory) + ", ESTIMATION: " + das_ua.getEstimationByFactory(factory) + ", comment: " + das_ua.getCommentByFactory(factory));
    	}
    }
    
    public static void test4(){
//    	WeeklyReportIntervalIterator wii = new WeeklyReportIntervalIterator(new Date());
//    	wii.getCurrent();
//    	wii.getNext();
    	
    	EMPScheduler empScheduler = new EMPScheduler("files/system/settings.xlsx", 5000);
    	Thread t = new Thread(empScheduler);
    	t.start();
    }
    
    public static void fillDB(int daysBeforeToday, int numOfDays){
    	EMPScheduler empHandler = new EMPScheduler("files/system/settings.xlsx", 5000);
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_YEAR, -daysBeforeToday);
    	persistInformationByDate(cal.getTime());
    	    	
    	for(int i = 0; i < numOfDays; i++){
    		cal.add(Calendar.DAY_OF_YEAR, -1);
    		persistInformationByDate(cal.getTime());
    		
    	}
    }
    
    public static void downloadFilesLoop(int daysBeforeToday, int numOfDays){
    	ArrayList<DownloadData> downloadList = new ArrayList<DownloadData>();
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.DAY_OF_YEAR, -daysBeforeToday);
    	addToDownloadListByDate(downloadList, cal.getTime());
    	    	
    	for(int i = 0; i < numOfDays; i++){
    		cal.add(Calendar.DAY_OF_YEAR, -1);
    		addToDownloadListByDate(downloadList, cal.getTime());
    	}
    	EMPDownloader empD = new EMPDownloader(downloadList);
    	empD.downloadFiles();
//    	downloadList
    }
    
    public static void addToDownloadListByDate(ArrayList<DownloadData> downloadList, Date date){
    	downloadList.add(new DownloadData("http://www.lagie.gr/fileadmin/user_upload/reports/DayAheadSchedulingResults/" + DateUtils.convertToString(date, "yyyyMMdd") + "_DayAheadSchedulingResults_01.xls", "files/excels/" + DateUtils.convertToString(date, "yyyyMMdd") + "_DayAheadSchedulingResults_01.xls", null));
    	downloadList.add(new DownloadData("http://www.lagie.gr/fileadmin/user_upload/reports/DayAheadSchedulingUnitAvailabilities/" + DateUtils.convertToString(date, "yyyyMMdd") + "_DayAheadSchedulingUnitAvailabilities_01.xls", "files/excels/" + DateUtils.convertToString(date, "yyyyMMdd") + "_DayAheadSchedulingUnitAvailabilities_01.xls", null));
    	downloadList.add(new DownloadData("http://www.admie.gr/fileadmin/user_upload/reports/WeekAheadWaterUsageDeclaration/" + DateUtils.convertToString(date, "yyyyMMdd") + "_WeekAheadWaterUsageDeclaration_01.xls", "files/excels/" + DateUtils.convertToString(date, "yyyyMMdd") + "_WeekAheadWaterUsageDeclaration_01.xls", null));
    	downloadList.add(new DownloadData("http://www.admie.gr/fileadmin/user_upload/reports/WeekAheadLoadForecast/" + DateUtils.convertToString(date, "yyyyMMdd") + "_WeekAheadLoadForecast_01.xls", "files/excels/" + DateUtils.convertToString(date, "yyyyMMdd") + "_WeekAheadLoadForecast_01.xls", null));
    }
    
    public static void persistInformationByDate(Date date) {
    	EMPScheduler empHandler = new EMPScheduler("files/system/settings.xlsx", 5000);
		String dateS = DateUtils.convertToString(date, "yyyyMMdd");
    	DAS_R_Parser parser = new DAS_R_Parser();
		parser.setParser("files/excels/" + dateS + "_DayAheadSchedulingResults_01.xls", dateS);
		ArrayList<DataRowEntity> info = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser.gatherData()){
			info.add((DataRowEntity) dbu);
		}
		
		WA_WUD_Parser parser2 = new WA_WUD_Parser();
		parser2.setParser("files/excels/" + dateS + "_WeekAheadWaterUsageDeclaration_01.xls", dateS);
		ArrayList<DataRowEntity> info2 = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser2.gatherData()){
			info2.add((DataRowEntity) dbu);
		}
		
		WA_LF_Parser parser3 = new WA_LF_Parser();
    	parser3.setParser("files/excels/" + dateS + "_WeekAheadLoadForecast_01.xls", dateS);
    	ArrayList<DataRowEntity> info3 = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser3.gatherData()){
			info3.add((DataRowEntity) dbu);
		}
		/**
		 * FOR AC
		 * auto edo ksesxoliase
		 */
    	
		DAS_UA_Parser parser4 = new DAS_UA_Parser();
		parser4.setParser("files/excels/" + dateS + "_DayAheadSchedulingUnitAvailabilities_01.xls", dateS);
    	ArrayList<UnitRowEntity> info4 = new ArrayList<UnitRowEntity>();
		for(DBUnit dbu : parser4.gatherData()){
			info4.add((UnitRowEntity) dbu);
		}
		
		DBTools dbTool = new DBTools();
		try {
			dbTool.startTool();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			
			DataEntity.insertDataRowEntities(info);
			DataEntity.insertDataRowEntities(info2);
			DataEntity.insertDataRowEntities(info3);
			/**
			 * FOR AC
			 * auto edo ksesxoliase
			 */
			UnitEntity.insertUnitRowEntities(info4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void persistInformation() {
    	EMPScheduler empHandler = new EMPScheduler("files/system/settings.xlsx", 5000);
		
    	DAS_R_Parser parser = new DAS_R_Parser();
		parser.setParser("files/excels/20120902_DayAheadSchedulingResults_01.xls", "20120902");
		ArrayList<DataRowEntity> info = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser.gatherData()){
			info.add((DataRowEntity) dbu);
		}
		
		WA_WUD_Parser parser2 = new WA_WUD_Parser();
		parser2.setParser("files/excels/20120902_WeekAheadWaterUsageDeclaration_01.xls", "20120902");
		ArrayList<DataRowEntity> info2 = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser2.gatherData()){
			info2.add((DataRowEntity) dbu);
		}
		
		WA_LF_Parser parser3 = new WA_LF_Parser();
    	parser3.setParser("files/excels/20120902_WeekAheadLoadForecast_01.xls", "20120902");
    	ArrayList<DataRowEntity> info3 = new ArrayList<DataRowEntity>();
		for(DBUnit dbu : parser3.gatherData()){
			info3.add((DataRowEntity) dbu);
		}
    	
		DAS_UA_Parser parser4 = new DAS_UA_Parser();
		parser4.setParser("files/excels/20120902_DayAheadSchedulingUnitAvailabilities_01.xls", "20120902");
    	ArrayList<UnitRowEntity> info4 = new ArrayList<UnitRowEntity>();
		for(DBUnit dbu : parser4.gatherData()){
			info4.add((UnitRowEntity) dbu);
		}
		
		DBTools dbTool = new DBTools();
		try {
			dbTool.startTool();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			DataEntity.insertDataRowEntities(info);
			DataEntity.insertDataRowEntities(info2);
			DataEntity.insertDataRowEntities(info3);
			UnitEntity.insertUnitRowEntities(info4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    public static void testWUParser(){
    	EMPScheduler empHandler = new EMPScheduler("files/system/settings.xlsx", 5000);
    	WA_WUD_Parser parser = new WA_WUD_Parser();
    	parser.setParser("files/excels/20120903_WeekAheadWaterUsageDeclaration_01.xls", "20120903");
    }
    
    public static void testLFParser(){
    	EMPScheduler empHandler = new EMPScheduler("files/system/settings.xlsx", 5000);
    	WA_LF_Parser parser = new WA_LF_Parser();
    	parser.setParser("files/excels/20120902_WeekAheadLoadForecast_01.xls", "20120902");
    }
    
    public static void testLog(){
    	DBTools dbTool = new DBTools();
		try {
			dbTool.startTool();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			LogEntity.insertLogRowEntity(new LogRowEntity("this is ...", 6, new Date(), 2, "moufa moufa sdfsdf"));
//			DataEntity.insertDataRowEntities(info);
//			DataEntity.insertDataRowEntities(info2);
//			DataEntity.insertDataRowEntities(info3);
//			UnitEntity.insertUnitRowEntities(info4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}