package com.fiserv.dps.excel.reader;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/jobber")
public class ExcelReaderController {
	@Autowired
	private StoreDataExcelService excelService;
	
	@GetMapping(path = "/hi")
	public ResponseEntity<String> sayHi() {
		return ResponseEntity.status(HttpStatus.OK).body("Hi there ...");
	}
	
	@PostMapping(path = "/uploadExcel")
	public ResponseEntity<String> loadStoreDataExcel(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (StoreDataExcelService.hasExcelFormat(file)) {
			try {
				excelService.readAndLoadStoreDataExcel(file.getInputStream());
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				System.out.println(message);
		        return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (IOException e) {
				e.printStackTrace();
				message = "Could not upload the file: " + file.getOriginalFilename();
				System.out.println(message);
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}
		}
		message = "Please upload an excel file with .xlsx format";
		System.out.println(message);
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
}
