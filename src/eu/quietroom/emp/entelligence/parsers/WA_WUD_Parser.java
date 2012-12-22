package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class WA_WUD_Parser extends ExcelParser implements Parser {
	private String dateString;
	private int totalsColIndex;
	
	public WA_WUD_Parser(){
		
	}
	
	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		this.dateString = dateString;
		int titlesRowIndex = ExcelUtils.getRowIndex(super.wbs, this.dateString + EMPSettings.getDAS_WU_SheetName(), EMPSettings.getDAS_WU_rowTitle());
		this.totalsColIndex = ExcelUtils.getColIndex(super.wbs, this.dateString + EMPSettings.getDAS_WU_SheetName(), EMPSettings.getDAS_WU_totalColName(), titlesRowIndex);
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		ArrayList<Integer> indexList = ExcelUtils.getRowIndexes(super.wbs, this.dateString + EMPSettings.getDAS_WU_SheetName(), EMPSettings.getDAS_WU_totalRowName());
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getDAS_R_DateFormat());
		int count = 0;
		for(Integer i : indexList){
			info.add(new DataRowEntity(date, EMPSettings.dbWaterUsageDeclaration, ++count, ExcelUtils.getNumericValue(super.wbs, this.dateString + EMPSettings.getDAS_WU_SheetName(), i.intValue(), totalsColIndex), ""));
		}
		return info;
	}

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_WA_WUD;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_DATA;
	}

}