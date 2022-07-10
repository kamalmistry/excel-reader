package com.fiserv.dps.excel.reader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	private void testExcelReader() {
		String excelFilePath = "D:\\Kamal\\Microservices Notes\\StoresLocation.xlsx";
		StoreDataExcelService sdr = new StoreDataExcelService();
		//sdr.readAndLoadStoreDataExcel(excelFilePath);
		//sdr.readAndLoadStoreDataExcel(excelFilePath);
		System.out.println("Done!");		
	}
}
