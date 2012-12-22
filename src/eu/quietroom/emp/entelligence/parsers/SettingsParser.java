package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class SettingsParser extends ExcelParser implements Parser{
	public SettingsParser(){
	}

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		EMPSettings.setExcelStoragePath(ExcelUtils.getValuesByRowName(super.wbs, "Storage Paths", "Excel Storage Path").get(0));
		
		EMPSettings.setParserMessage(ExcelUtils.getValuesByRowName(super.wbs, "Message Strings", "PARSER MESSAGE").get(0));
		
		EMPSettings.setLigniteFactories(ExcelUtils.getValuesByRowName(super.wbs, "Factories", "LIGNITE PRODUCTION"));
		EMPSettings.setCcgtFactories(ExcelUtils.getValuesByRowName(super.wbs, "Factories", "CCGT PRODUCTION"));
		EMPSettings.setHydroFactories(ExcelUtils.getValuesByRowName(super.wbs, "Factories", "HYDRO PRODUCTION"));
		
		EMPSettings.setDailyReport_startHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "DR publication hour").get(0)));
		EMPSettings.setDailyReport_startMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "DR publication minute").get(0)));
		EMPSettings.setDailyReport_endHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "DR deadline hour").get(0)));
		EMPSettings.setDailyReport_endMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "DR deadline minute").get(0)));
		
		EMPSettings.setWeeklyReport_startHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "WR publication hour").get(0)));
		EMPSettings.setWeeklyReport_startMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "WR publication minute").get(0)));
		EMPSettings.setWeeklyReport_endHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "WR deadline hour").get(0)));
		EMPSettings.setWeeklyReport_endMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Reports Settings", "WR deadline minute").get(0)));
		
		EMPSettings.setDAS_R_Suffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS Results file suffix").get(0));
		EMPSettings.setDAS_R_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS Results URL").get(0));
		EMPSettings.setDAS_R_DaysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS Results num of days").get(0));
		EMPSettings.setDAS_R_DateFormat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS Results date format").get(0));
		
		EMPSettings.setDAS_R_SheetSuffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS Results sheet Suffix").get(0));
		
		EMPSettings.setDAS_R_1_1StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS R publication hour").get(0)));
		EMPSettings.setDAS_R_1_1StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS R publication minute").get(0)));
		EMPSettings.setDAS_R_1_1EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS R deadline hour").get(0)));
		EMPSettings.setDAS_R_1_1EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 DAS R deadline minute").get(0)));

		EMPSettings.setDAS_R_LoadDeclarationsRowName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 Row - Load Declarations + Losses").get(0));
		EMPSettings.setDAS_R_LossesRowName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 Row - Losses").get(0));
		EMPSettings.setDAS_R_SmpRowName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 Row - SMP").get(0));
		EMPSettings.setDAS_R_RenewablesRowName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 Row - Renewables").get(0));
		EMPSettings.setDAS_R_totalSchedules(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.1 Row - Total Schedules").get(0));
		
		EMPSettings.setDAS_UA_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA URL").get(0));
		EMPSettings.setDAS_UA_FileSuffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA suffix").get(0));
		EMPSettings.setDAS_UA_DaysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA num of days").get(0));
		EMPSettings.setDAS_UA_DateFormat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA date format").get(0));
		
		EMPSettings.setDAS_UA_SheetName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA sheet name").get(0));
		
		EMPSettings.setDAS_UA_1_3StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA publication hour").get(0)));
		EMPSettings.setDAS_UA_1_3StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA publication minute").get(0)));
		EMPSettings.setDAS_UA_1_3EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA deadline hour").get(0)));
		EMPSettings.setDAS_UA_1_3EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA deadline minute").get(0)));
		
		EMPSettings.setDAS_UA_rowTitle(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA title row name").get(0));
		
		EMPSettings.setDAS_UA_colUnitName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA unit column name").get(0));
		EMPSettings.setDAS_UA_colMaxNet(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA max net column name").get(0));
		EMPSettings.setDAS_UA_colEstimation(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA estimation column name").get(0));
		EMPSettings.setDAS_UA_colComments(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA comments column name").get(0));
		
		EMPSettings.setDAS_WA_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD URL").get(0));
		EMPSettings.setDAS_WA_DaysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD num of days").get(0));
		EMPSettings.setDAS_WA_DateFormat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD date format").get(0));
		EMPSettings.setDAS_WA_suffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD suffix").get(0));
		
		EMPSettings.setDAS_WU_SheetName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD sheet name").get(0));
		EMPSettings.setDAS_WU_rowTitle(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD row title").get(0));
		EMPSettings.setDAS_WU_totalRowName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD total row name").get(0));
		EMPSettings.setDAS_WU_totalColName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD total col name").get(0));
		
		EMPSettings.setDAS_WU_1_4_StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD publication hour").get(0)));
		EMPSettings.setDAS_WU_1_4_StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD publication minute").get(0)));
		EMPSettings.setDAS_WU_1_4_EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD deadline hour").get(0)));
		EMPSettings.setDAS_WU_1_4_EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.4 WA WUD deadline minute").get(0)));

		EMPSettings.setDAS_LF_1_5_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF URL").get(0));
		EMPSettings.setDAS_LF_1_5_DaysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF num of days").get(0));
		EMPSettings.setDAS_LF_1_5_DateFormat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF date format").get(0));
		EMPSettings.setDAS_LF_1_5_Suffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF suffix").get(0));
		EMPSettings.setDAS_LF_1_5_SheetName(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF sheetname").get(0));
		EMPSettings.setDAS_LF_1_5_TitlesRowTitle(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF titles row title").get(0));
		
		EMPSettings.setDAS_LF_1_5_StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF publication hour").get(0)));
		EMPSettings.setDAS_LF_1_5_StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF publication minute").get(0)));
		EMPSettings.setDAS_LF_1_5_EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.5 WA LF deadline minute").get(0)));
		EMPSettings.setDAS_LF_1_5_EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "1.3 DAS UA deadline minute").get(0)));
		
		EMPSettings.setWeatherForecast_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast URL").get(0));
		EMPSettings.setWeatherForecast_daysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast num of days").get(0));
		EMPSettings.setWeatherForecast_suffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast suffix").get(0));
		
		EMPSettings.setWeatherForecast_StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast publication hour").get(0)));
		EMPSettings.setWeatherForecast_StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast publication minute").get(0)));
		EMPSettings.setWeatherForecast_EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast deadline hour").get(0)));
		EMPSettings.setWeatherForecast_EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "WeatherForecast deadline minute").get(0)));
		
		EMPSettings.setBNS_STATS_3_1_URL(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS URL").get(0));
		EMPSettings.setBNS_STATS_3_1_FileSuffix(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS suffix").get(0));
		EMPSettings.setBNS_STATS_3_1_DaysAhead(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS num of days").get(0));
		EMPSettings.setBNS_STATS_3_1_DateFormat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS date format").get(0));
		
		EMPSettings.setBNS_STATS_3_1_StartHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS publication hour").get(0)));
		EMPSettings.setBNS_STATS_3_1_StartMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS publication minute").get(0)));
		EMPSettings.setBNS_STATS_3_1_EndHour((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS deadline hour").get(0)));
		EMPSettings.setBNS_STATS_3_1_EndMinute((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS deadline minute").get(0)));

		EMPSettings.setBNS_STATS_3_1_sheet_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS sheet name").get(0));
		EMPSettings.setBNS_STATS_3_1_column_titles_row_index((int)Float.parseFloat(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS column titles row index").get(0)));
		EMPSettings.setBNS_STATS_3_1_date_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS date column name").get(0));
		EMPSettings.setBNS_STATS_3_1_product_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS product column name").get(0));
		EMPSettings.setBNS_STATS_3_1_daily_closing_price_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS daily closing price column name").get(0));
		EMPSettings.setBNS_STATS_3_1_variation_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS variation column name").get(0));
		EMPSettings.setBNS_STATS_3_1_listed_volume_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS listed volume column name").get(0));
		EMPSettings.setBNS_STATS_3_1_otc_volume_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS otc volume column name").get(0));
		EMPSettings.setBNS_STATS_3_1_total_volume_column_name(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS total volume column name").get(0));

		EMPSettings.setBNS_STATS_3_1_BNS_CER(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS CER").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_CER_LH(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS CER-LH").get(0));
		EMPSettings.setBNS_STATS_3_1_Spread_outright_BNS_CER_EUA(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS Spread (outright) BNS CER/EUA").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_ERU(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS ERU").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_ERU_LH(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS ERU-LH").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_EUA_08_12(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS EUA 08-12").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_EUAA(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS EUAA").get(0));
		EMPSettings.setBNS_STATS_3_1_BNS_GREEN_CER(ExcelUtils.getValuesByRowName(super.wbs, "Files Names", "3.1 BNS STATS BNS GREEN CER").get(0));

		return null;
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
