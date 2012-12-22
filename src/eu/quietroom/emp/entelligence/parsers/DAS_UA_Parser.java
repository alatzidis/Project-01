package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.UnitRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;


public class DAS_UA_Parser extends ExcelParser implements Parser{
	private String dateString;
	private int titleRowIndex;
	private ArrayList<String> factoriesList;
	private ArrayList<String> maxNetList;
	private ArrayList<String> estimationList;
	private ArrayList<String> commentsList;
	
	public DAS_UA_Parser(){
	}
	
	public ArrayList<String> getFactoriesWithComments(){
		ArrayList<String> out = new ArrayList<String>();
		int counter = 0;
		for(String comment : commentsList){
			if(!comment.equals("")){
				out.add(factoriesList.get(counter));
			}
			counter++;
		}
		return out;
	}
	
	public String getMaxNetByFactory(String factoryName){
		int index = factoriesList.indexOf(factoryName);
		if(index > -1){
			return maxNetList.get(index);
		}else{
			return "";
		}
	}
	
	public String getEstimationByFactory(String factoryName){
		int index = factoriesList.indexOf(factoryName);
		if(index > -1){
			return estimationList.get(index);
		}else{
			return "";
		}
	}
	
	public String getCommentByFactory(String factoryName){
		int index = factoriesList.indexOf(factoryName);
		if(index > -1){
			return commentsList.get(index);
		}else{
			return "";
		}
	}

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		this.dateString = dateString; 
		this.titleRowIndex = ExcelUtils.getRowIndex(super.wbs, EMPSettings.getDAS_UA_SheetName(), EMPSettings.getDAS_UA_rowTitle());
		this.factoriesList = ExcelUtils.getValuesByColName(super.wbs, EMPSettings.getDAS_UA_SheetName(), EMPSettings.getDAS_UA_colUnitName(), titleRowIndex);
		this.maxNetList = ExcelUtils.getValuesByColName(super.wbs, EMPSettings.getDAS_UA_SheetName(), EMPSettings.getDAS_UA_colMaxNet(), titleRowIndex);
		this.estimationList = ExcelUtils.getValuesByColName(super.wbs, EMPSettings.getDAS_UA_SheetName(), EMPSettings.getDAS_UA_colEstimation(), titleRowIndex);
		this.commentsList = ExcelUtils.getValuesByColName(super.wbs, EMPSettings.getDAS_UA_SheetName(), EMPSettings.getDAS_UA_colComments(), titleRowIndex);
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getDAS_UA_DateFormat());
		for(String factory : getFactoriesWithComments()){
			info.add(new UnitRowEntity(date, factory, Double.parseDouble(getMaxNetByFactory(factory)), getCommentByFactory(factory)));
    	}
		return info;
	}

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_DAS_UA;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_UNIT;
	}
}
