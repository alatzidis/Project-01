package eu.quietroom.emp.entelligence.parsers;

import org.apache.poi.ss.usermodel.Workbook;

import eu.quietroom.emp.utils.excelPackage.ExcelUtils;


public abstract class ExcelParser{
	protected Workbook wbs;
	
	public ExcelParser(){
	}
	
	public void initParser(String filePath){
		this.wbs = ExcelUtils.getExcel(filePath);
	}
}
