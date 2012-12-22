package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class WeeklyPowerReportCustoDataParser extends ExcelParser implements Parser{
	private Date currentDate;

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		currentDate = DateUtils.getDateByString(dateString, "yyyyMMdd");
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		info.add(new DataRowEntity(currentDate, EMPSettings.dbWeeklyPower_comments, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Weekly Power Custom Data", "Comments").get(0)));
		return info;
	}

	@Override
	public String getLogMessage() {
		return "settings_parse";
	}

	@Override
	public String getDBType() {
		// TODO Auto-generated method stub
		return null;
	}
}
