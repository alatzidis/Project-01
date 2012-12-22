package eu.quietroom.emp.entelligence.parsers;

import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.entelligence.EMPSettings;
import eu.quietroom.emp.entelligence.IndexRange;
import eu.quietroom.emp.entelligence.dbaccess.DBUnit;
import eu.quietroom.emp.entelligence.dbaccess.DataRowEntity;
import eu.quietroom.emp.utils.dateUtils.DateUtils;
import eu.quietroom.emp.utils.excelPackage.ExcelUtils;

public class BNS_STATS_parser extends ExcelParser implements Parser {
	private String dateString;
	private String modDateString;
	private int productColIndex;
	private int dailyClosingPriceColIndex;
	private int variationColIndex;
	private int listedVolumeColIndex;
	private int otcVolumeColIndex;
	private int totalVolumeColIndex;
	
	public BNS_STATS_parser(){
		
	}
	
	private IndexRange getRowRangeByDate(){
		return  getRowRangeByDate(this.modDateString);
	}
	
	private IndexRange getRowRangeByDate(String _dateString){
		int startIndex = -1;
		int endIndex = -1;
		IndexRange out = null;
		int colTitlesIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_date_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		ArrayList<String> datesArray = ExcelUtils.getValuesByColName(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_date_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		int counter = 0;
		for(String cDate : datesArray){
			if(_dateString.equals(cDate)){
				if(startIndex == -1){
					startIndex = counter;
				}
			}else{
				if(startIndex != -1 && endIndex == -1){
					endIndex = counter;
					break;
				}
			}
			counter++;
		}
		if(startIndex != -1 && endIndex != -1){
			out = new IndexRange(startIndex + colTitlesIndex + 1, endIndex + colTitlesIndex + 1);
		}else{
			out = new IndexRange(0, 0);
		}
//		System.out.println("startIndex: " + startIndex + " endIndex: " + endIndex);
//		for(int i = startIndex + colTitlesIndex + 1; i < endIndex + colTitlesIndex + 1; i++){
//			System.out.println(datesArray.get(i));
//		}
		return out;
	}
	
	private ArrayList<DataRowEntity> getValues(String dbIdentifier, int rowIndex, IndexRange range, Date date){
		ArrayList<DataRowEntity> out = new ArrayList<DataRowEntity>();
		String prefix = EMPSettings.dbBNSPrefix + EMPSettings.dbDivider;
//		int rowIndex = ;
		out.add(new DataRowEntity(date, prefix + dbIdentifier + EMPSettings.dbDivider + EMPSettings.dbBNS_Daily_Closing_Price, 0, ExcelUtils.getNumericValue(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), rowIndex, dailyClosingPriceColIndex), ""));
		out.add(new DataRowEntity(date, prefix + dbIdentifier + EMPSettings.dbDivider + EMPSettings.dbBNS_Variation, 0, ExcelUtils.getNumericValue(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), rowIndex, variationColIndex), ""));
		out.add(new DataRowEntity(date, prefix + dbIdentifier + EMPSettings.dbDivider + EMPSettings.dbBNS_Listed_Volume, 0, ExcelUtils.getNumericValue(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), rowIndex, listedVolumeColIndex), ""));
		out.add(new DataRowEntity(date, prefix + dbIdentifier + EMPSettings.dbDivider + EMPSettings.dbBNS_Otc_Volume, 0, ExcelUtils.getNumericValue(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), rowIndex, otcVolumeColIndex), ""));
		out.add(new DataRowEntity(date, prefix + dbIdentifier + EMPSettings.dbDivider + EMPSettings.dbBNS_Total_Volume, 0, ExcelUtils.getNumericValue(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), rowIndex, totalVolumeColIndex), ""));
		return out;
	}

	@Override
	public void setParser(String filePath, String dateString) {
		super.initParser(filePath);
		this.dateString = dateString;
		this.modDateString = dateString.substring(6, 8) + "/" + dateString.substring(4, 6) + "/" + dateString.substring(0, 4);
		this.productColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_product_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		this.dailyClosingPriceColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_daily_closing_price_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		this.variationColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_variation_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		this.listedVolumeColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_listed_volume_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		this.otcVolumeColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_otc_volume_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
		this.totalVolumeColIndex = ExcelUtils.getColIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_total_volume_column_name(), EMPSettings.getBNS_STATS_3_1_column_titles_row_index());
	}

	@Override
	public ArrayList<DBUnit> gatherData() {
		ArrayList<DBUnit> info = new ArrayList<DBUnit>();
		Date date = DateUtils.getDateByString(dateString, EMPSettings.getBNS_STATS_3_1_DateFormat());
//		System.out.println(dateString);
		IndexRange range = getRowRangeByDate();
		info.addAll(getValues(EMPSettings.dbBNS_CER, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_CER(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_CER_LH, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_CER_LH(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNSSpread_outright_BNS_CER_EUA, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_Spread_outright_BNS_CER_EUA(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_ERU, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_ERU(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_ERU_LH, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_ERU_LH(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_EUA_08_12, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_EUA_08_12(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_EUAA, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_EUAA(), productColIndex, range.getStart(), range.getEnd()), range, date));
		info.addAll(getValues(EMPSettings.dbBNS_GREEN_CER, ExcelUtils.getRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), EMPSettings.getBNS_STATS_3_1_BNS_GREEN_CER(), productColIndex, range.getStart(), range.getEnd()), range, date));
//		for(int i = range.getStart(); i < range.getEnd(); i++){
//			System.out.println(ExcelUtils.getValuesByRowIndex(super.wbs, EMPSettings.getBNS_STATS_3_1_sheet_name(), i));
//		}
//		System.out.println();
		return info;
	}

	@Override
	public String getLogMessage() {
		return EMPSettings.t_p_BNS_STATS;
	}

	@Override
	public String getDBType() {
		return EMPSettings.DB_TYPE_DATA;
	}

}
