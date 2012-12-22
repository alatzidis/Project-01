package eu.quietroom.emp.entelligence.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class WeatherForecast_Parser implements Parser {
	private Document doc;
	private String filePath;
	private String dateString;
	
	@Override
	public void setParser(String filePath, String dateString) {
		this.filePath = filePath;
		this.dateString = dateString;
		File input = new File(this.filePath);
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> out = new ArrayList<DBUnit>();
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getDAS_R_DateFormat());
		ArrayList<Integer> minTemps = getMinTemperatures();
		int stepCounter;
		stepCounter = 1;
		for(Integer i : minTemps){
			if(stepCounter > 0){
				out.add(new DataRowEntity(date, EMPSettings.dbWeatherPrefix + EMPSettings.dbDivider + EMPSettings.dbTemperature + EMPSettings.dbDivider + EMPSettings.dbTempMin, stepCounter, (double)i, ""));
			}
			stepCounter++;
		}
		
		ArrayList<Integer> maxTemps = getMaxTemperatures();
		stepCounter = 1;
		for(Integer i : maxTemps){
			if(stepCounter > 0){
				out.add(new DataRowEntity(date, EMPSettings.dbWeatherPrefix + EMPSettings.dbDivider + EMPSettings.dbTemperature + EMPSettings.dbDivider + EMPSettings.dbTempMax, stepCounter, (double)i, ""));
			}
			stepCounter++;
		}
		
		ArrayList<Double> windSpeeds = getWindSpeed();
		stepCounter = 1;
		for(Double value : windSpeeds){
				out.add(new DataRowEntity(date, EMPSettings.dbWeatherPrefix + EMPSettings.dbDivider + EMPSettings.dbWindSpeed, stepCounter, value, ""));
			stepCounter++;
		}
		
		ArrayList<Double> chanceOfPrecipitation = getPrecipitationChance();
		stepCounter = 1;
		for(Double value : chanceOfPrecipitation){
				out.add(new DataRowEntity(date, EMPSettings.dbWeatherPrefix + EMPSettings.dbDivider + EMPSettings.dbChanceOfPrecipitation, stepCounter, value, ""));
			stepCounter++;
		}
				
		ArrayList<Integer> sunnyHours = getSunnyHours();
		stepCounter = 1;
		for(Integer value : sunnyHours){
				out.add(new DataRowEntity(date, EMPSettings.dbWeatherPrefix + EMPSettings.dbDivider + EMPSettings.dbSunnyHours, stepCounter, (double)value, ""));
			stepCounter++;
		}
		
		return out;
	}
	
	public ArrayList<Integer> getMaxTemperatures(){
    	return getTemperatures(0);
    }
    
    public ArrayList<Integer> getMinTemperatures(){
    	return getTemperatures(1);
    }
    
    public ArrayList<Integer> getTemperatures(int type){
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
    
    public ArrayList<Double> getWindSpeed(){
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
    
    public ArrayList<Double> getPrecipitationChance(){
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
    
    public ArrayList<Integer> getSunnyHours(){
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

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_weather;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_DATA;
	}

}
