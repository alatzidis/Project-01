package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.StringToNumber;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class WA_LF_Parser extends ExcelParser implements Parser {
	private String dateString;
	private final int maxDays= 25;
	private int titlesRowIndex;
	
	public WA_LF_Parser(){
		
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
			System.out.println("###### ERROR!!! "
			+ rowName + " HAS NO VALUES ######");
		}
		return out;
	}
	
	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		this.dateString = dateString;
		this.titlesRowIndex = ExcelUtils.getRowIndex(super.wbs, EMPSettings.getDAS_LF_1_5_SheetName(), EMPSettings.getDAS_LF_1_5_TitlesRowTitle());
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getDAS_R_DateFormat());
		for(int i = 1; i <= 7; i++){
			ArrayList<Double> valuesList = takeOnlyHourValues("moufa", ExcelUtils.getValuesByRowIndex(super.wbs, EMPSettings.getDAS_LF_1_5_SheetName(), titlesRowIndex + i));
			double value = 0;
			int counter = 0;
			for(Double d : valuesList){
				counter++;
				value += d.doubleValue();
			}
			value *= (double)1/(double)counter;
			info.add(new DataRowEntity(date, EMPSettings.dbLoadForecast, i, value, ""));
		}
		return info;
	}

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_WA_LF;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_DATA;
	}

}
