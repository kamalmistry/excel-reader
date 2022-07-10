package com.fiserv.dps.excel.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StoreDataExcelService {
	public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	public static boolean hasExcelFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public void readAndLoadStoreDataExcel(String excelFilePath) {
		File file = new File(excelFilePath); // creating a new file instance
		try {
			readAndLoadStoreDataExcel(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readAndLoadStoreDataExcel(InputStream is) {
		try {
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(is);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			if(itr.hasNext()) {
				// This is a header row, so ignore it.
				System.out.println(itr.next().toString());
			}
			while (itr.hasNext()) {
				Row row = itr.next();
				//displayRowData(row);
				
				StoreDTO store = getStoreDtoObjFromRow(row);
				System.out.println(store);
				
				/*
				 * TODO: Load this store into database ...
				 */
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
		}
	}
	
	private void displayRowData(Row row) {
		System.out.println("0 --> " + row.getCell(0).getCellType());
		System.out.println("1 --> " + row.getCell(1).getCellType());
		System.out.println("2 --> " + row.getCell(2).getCellType());
		System.out.println("3 --> " + row.getCell(3).getCellType());
		System.out.println("4 --> " + row.getCell(4).getCellType());
		System.out.println("5 --> " + row.getCell(5).getCellType());
		System.out.println("6 --> " + row.getCell(6).getCellType());
	}
	private StoreDTO getStoreDtoObjFromRow(Row row) {
		StoreDTO store = new StoreDTO();
		// Ignore column number 0, since we don't need it in StoreDTO
		
		store.setStoreName(row.getCell(1).getStringCellValue());
		store.setAddress(row.getCell(2).getStringCellValue());
		store.setCity(row.getCell(3).getStringCellValue());
		store.setZipcode(row.getCell(4).getStringCellValue());
//		String merchantIdStr = row.getCell(5).getStringCellValue();
//		store.setMerchantId(Long.getLong(merchantIdStr));
		store.setMerchantId((long)(row.getCell(5).getNumericCellValue()));
		store.setIsOpen(row.getCell(6).getBooleanCellValue());
		
		return store;
	}
	
	private void iterateOverRowCells(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			switch (cell.getCellType()) {
			case STRING: // field that represents string cell type
				System.out.print(cell.getStringCellValue() + "\t\t");
				break;
			case NUMERIC: // field that represents number cell type
				System.out.print(cell.getNumericCellValue() + "\t\t");
				break;
			case BOOLEAN:
				if (cell.getBooleanCellValue()) {
					System.out.print(true + "\t\t");
				} else {
					System.out.print(false + "\t\t");
				}
				break;
			default:
				System.out.print(">>" + cell.getStringCellValue());
				break;
			}
		}
		System.out.println("");
	}
}
