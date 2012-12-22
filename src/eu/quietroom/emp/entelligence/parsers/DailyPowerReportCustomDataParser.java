package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class DailyPowerReportCustomDataParser extends ExcelParser implements Parser{
	private Date currentDate;

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		currentDate = DateUtils.getDateByString(dateString, "yyyyMMdd");
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_top, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Comment top").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_bottom, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Comment bottom").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bd1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Day 1 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pd1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Day 1 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_od1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Day 1 off peak").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bwa, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Weekend Ahead base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pwa, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Weekend Ahead peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_owa, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Weekend Ahead off peak").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bw1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 1 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pw1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 1 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_ow1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 1 off peak").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bw2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 2 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pw2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 2 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_ow2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "W 2 off peak").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bm2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 2 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pm2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 2 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_om2, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 2 off peak").get(0)));
		
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_bm3, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 3 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_pm3, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 3 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_om3, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "M 3 off peak").get(0)));

		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_by1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Year 1 base").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_py1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Year 1 peak").get(0)));
		info.add(new DataRowEntity(currentDate, EMPSettings.dbDailyPower_array_oy1, 0, 0, ExcelUtils.getValuesByRowName(super.wbs, "Daily Power Custom Data", "Year 1 off peak").get(0)));

		// TODO Auto-generated method stub
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

