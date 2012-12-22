package eu.quietroom.emp.utils.excelPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static void readExcel(String filename){
		Workbook wbs;
		
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(filename);
			if(filename.charAt(filename.length() - 1) == 'x'){
				wbs = new XSSFWorkbook(fis);
			}else{
				wbs = new HSSFWorkbook(fis);
			}
			ArrayList<ArrayList<ArrayList<Cell>>> excel = new ArrayList<ArrayList<ArrayList<Cell>>>();
			for(int i = 0; i < wbs.getNumberOfSheets(); i++){
				ArrayList<ArrayList<Cell>> sheetData = new ArrayList<ArrayList<Cell>>();
				Sheet sheet = wbs.getSheetAt(i);
				System.out.println("sheet name: " + sheet.getSheetName());
				Iterator<Row> itR = sheet.rowIterator();
				while(itR.hasNext()){
					Row cRow = itR.next();
					ArrayList<Cell> cellList = new ArrayList<Cell>();
					Iterator<Cell> itC = cRow.cellIterator();
					while(itC.hasNext()){
						Cell cCell = itC.next();
						cellList.add(cCell);
					}
					sheetData.add(cellList);
				}
				excel.add(sheetData);
			}
			System.out.println(printExcel(excel));
		}catch(IOException e){
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
	}
	
	public static Workbook getExcel(String filename){
		Workbook wbs;
		
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(filename);
			if(filename.charAt(filename.length() - 1) == 'x'){
				wbs = new XSSFWorkbook(fis);
			}else{
				wbs = new HSSFWorkbook(fis);
			}
			return wbs;
		}catch(IOException e){
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        return null;
	}
	/*
	public static Sheet getSheetByName(Workbook wbs, String sheetName){
		for(int i = 0; i < wbs.getNumberOfSheets(); i++){
		}
	}
	*/
	
	public static ArrayList<String> getValuesByRowName(Workbook wbs, String sheetName, String rowName/*, int maxColIndex*/){
		ArrayList<String> out = new  ArrayList<String>();
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row cRow = itR.next();
				if(cRow.getCell(0).toString().equals(rowName)){
					for(int i = 1; i < cRow.getPhysicalNumberOfCells(); i++){
						out.add(cRow.getCell(i).toString());
					}
				}
			}
		}
		return out;
	}
	
	public static ArrayList<String> getValuesByRowIndex(Workbook wbs, String sheetName, int rowIndex/*, int maxColIndex*/){
		ArrayList<String> out = new  ArrayList<String>();
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Row cRow = sheet.getRow(rowIndex);
			for(int i = 0; i < cRow.getPhysicalNumberOfCells(); i++){
				out.add(cRow.getCell(i).toString());
			}
		}
		return out;
	}
	
	public static ArrayList<String> getValuesByColName(Workbook wbs, String sheetName, String colName, int rowIndex){
		ArrayList<String> out = new  ArrayList<String>();
		int colIndex = getColIndex(wbs, sheetName, colName, rowIndex);
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null && colIndex > -1){
			int counter = 0;
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row row = itR.next();
				if(counter > rowIndex){
					out.add(row.getCell(colIndex).toString());
				}
				counter++;
			}
		}
		return out;
	}
	
	public static int getRowIndex(Workbook wbs, String sheetName, String rowName){
		/*
		int counter = 0;
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row cRow = itR.next();
				if(cRow.getPhysicalNumberOfCells() > 0 && cRow.getCell(0).toString().equals(rowName)){
					return counter;
				}
				counter++;
			}
		}
		return -1;
		*/
		return getRowIndex(wbs, sheetName, rowName, 0);
	}
	
	public static int getRowIndex(Workbook wbs, String sheetName, String rowName, int colTitleIndex){
		/*
		int counter = 0;
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row cRow = itR.next();
				if(cRow.getPhysicalNumberOfCells() > 0 && cRow.getCell(colTitleIndex).toString().equals(rowName)){
					return counter;
				}
				counter++;
			}
		}
		return -1;
		*/
		return getRowIndex(wbs, sheetName, rowName, 0, 0, -1);
	}
	
	public static int getRowIndex(Workbook wbs, String sheetName, String rowName, int colTitleIndex, int startIndex, int endIndex){
		int counter = 0;
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row cRow = itR.next();
				if(cRow.getPhysicalNumberOfCells() > 0 && cRow.getCell(colTitleIndex).toString().equals(rowName) && counter >= startIndex){
					return counter;
				}
				counter++;
				if(endIndex > -1 && counter > endIndex){
					break;
				}
			}
		}
		return -1;
	}
	
	public static ArrayList<Integer> getRowIndexes(Workbook wbs, String sheetName, String rowName){
		ArrayList<Integer> out = new ArrayList<>();
		int counter = 0;
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Iterator<Row> itR = sheet.rowIterator();
			while(itR.hasNext()){
				Row cRow = itR.next();				
				if(cRow.getPhysicalNumberOfCells() > 0 && cRow.getCell(0).toString().equals(rowName)){
					out.add(new Integer(counter));
				}
				counter++;
			}
		}
		return out;
	}
	
	public static int getColIndex(Workbook wbs, String sheetName, String colName, int rowIndex){
		int counter = 0;
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Row row = sheet.getRow(rowIndex);
			Iterator<Cell> itR = row.cellIterator();
			while(itR.hasNext()){
				Cell cell = itR.next();
				if(cell.toString().equals(colName)){
					return counter;
				}
				counter++;
			}
		}
		return -1;
	}
	
	public static String getValue(Workbook wbs, String sheetName, int rowIndex, int colIndex){
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Row row = sheet.getRow(rowIndex);
			Cell cell = row.getCell(colIndex);
			return cell.getStringCellValue();
		}
		return "";
	}
	
	public static double getNumericValue(Workbook wbs, String sheetName, int rowIndex, int colIndex){
		Sheet sheet = wbs.getSheet(sheetName);
		if(sheet != null){
			Row row = sheet.getRow(rowIndex);
			Cell cell = row.getCell(colIndex);
			return cell.getNumericCellValue();
		}
		return -1;
	}
	
	public static String printRow(ArrayList<Cell> row){
		String out = "";
		for(Cell c : row){
			out += c.toString() + ", ";
		}
		return out;
	}
	
	public static String printSheet(ArrayList<ArrayList<Cell>> sheet){
		String out = "";
		for(ArrayList<Cell> row: sheet){
			out += printRow(row) + "\n";
		}
		return out;
	}
	
	public static String printExcel(ArrayList<ArrayList<ArrayList<Cell>>> excel){
		String out = "";
		for(ArrayList<ArrayList<Cell>> sheet : excel){
			out += printSheet(sheet);
		}
		return out;
	}

}
