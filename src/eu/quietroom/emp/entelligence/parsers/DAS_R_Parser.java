package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.StringToNumber;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;


public class DAS_R_Parser extends ExcelParser implements Parser {
	private String dateString;
	private final int maxDays= 25;
//	private ArrayList<ParserMessage> messageList;
	
	public DAS_R_Parser(){

	}
	
	public ArrayList<Double> getLoadDeclarationsValues(){
		return getHourValuesByRow(EMPSettings.getDAS_R_LoadDeclarationsRowName());
	}
	
	public ArrayList<Double> getLossesValues(){
		return getHourValuesByRow(EMPSettings.getDAS_R_LossesRowName());
	}
	
	public ArrayList<Double> getSMPValues(){
		return getHourValuesByRow(EMPSettings.getDAS_R_SmpRowName());
	}
	
	public ArrayList<Double> getRenewablesValues(){
		return getHourValuesByRow(EMPSettings.getDAS_R_RenewablesRowName());
	}
	
	public ArrayList<Double> getTotalSchedules(){
		return getHourValuesByRow(EMPSettings.getDAS_R_totalSchedules());
	}
	
	public ArrayList<Double> getHourValuesByRow(String rowName){
		ArrayList<Double> out = new ArrayList<Double>();
		ArrayList<String> stringList = ExcelUtils.getValuesByRowName(super.wbs, dateString + EMPSettings.getDasSheetSuffix(), rowName);
		out = takeOnlyHourValues(rowName, stringList);
		return out;
	}
	
	private ArrayList<Double> takeOnlyHourValues(String rowName, ArrayList<String> stringList){
		ArrayList<Double> out = new ArrayList<Double>();
		if(stringList.size() > 0){
			int counter = 0;
			while(counter < maxDays){
				String s = stringList.get(counter);
				if(StringToNumber.isNumber(s)){
					out.add(Double.parseDouble(s));
				}
				counter++;
			}
		}else{
			//TODO Na vazei error message
			System.out.println("###### ERROR!!! " + rowName + " HAS NO VALUES ######");
		}
		return out;
	}

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		this.dateString = dateString;
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		ArrayList<Double> doublesList;
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getDAS_R_DateFormat());
		int count;
		
		doublesList = this.getSMPValues();
		count = 0;
		for(double d : doublesList){
			info.add(new DataRowEntity(date, EMPSettings.dbSMP, ++count, d, ""));
		}
		
		doublesList = this.getLoadDeclarationsValues();
		count = 0;
		for(double d : doublesList){
			info.add(new DataRowEntity(date, EMPSettings.dbLoad, ++count, d, ""));
		}
		
		doublesList = this.getLossesValues();
		count = 0;
		for(double d : doublesList){
			info.add(new DataRowEntity(date, EMPSettings.dbLoss, ++count, d, ""));
		}
		
		doublesList = this.getRenewablesValues();
		count = 0;
		for(double d : doublesList){
			info.add(new DataRowEntity(date, EMPSettings.dbUnitPrefix + EMPSettings.dbDivider + EMPSettings.dbRenewables, ++count, d, ""));
		}
		
		doublesList = this.getTotalSchedules();
		count = 0;
		for(double d : doublesList){
			info.add(new DataRowEntity(date, EMPSettings.dbUnitPrefix + EMPSettings.dbDivider + EMPSettings.dbImportsExports, ++count, d, ""));
		}
		
		for(String s : EMPSettings.getLigniteFactories()){
			ArrayList<Double> valuesList = getHourValuesByRow(s);
			count = 0;
			for(double d : valuesList){
				info.add(new DataRowEntity(date, EMPSettings.dbUnitPrefix + EMPSettings.dbDivider + EMPSettings.dbLignite + EMPSettings.dbDivider + s, ++count, d, ""));
			}
    	}
		
		for(String s : EMPSettings.getHydroFactories()){
			ArrayList<Double> valuesList = getHourValuesByRow(s);
			count = 0;
			for(double d : valuesList){
				info.add(new DataRowEntity(date, EMPSettings.dbUnitPrefix + EMPSettings.dbDivider + EMPSettings.dbHydro + EMPSettings.dbDivider + s, ++count, d, ""));
			}
    	}
		
		for(String s : EMPSettings.getCcgtFactories()){
			ArrayList<Double> valuesList = getHourValuesByRow(s);
			count = 0;
			for(double d : valuesList){
				info.add(new DataRowEntity(date, EMPSettings.dbUnitPrefix + EMPSettings.dbDivider + EMPSettings.dbCCGT + EMPSettings.dbDivider + s, ++count, d, ""));
			}
    	}
		
		return info;
	}

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_DAS_R;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_DATA;
	}
}