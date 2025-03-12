package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.UserCredentials;

public class ExcelDataUtility {
	

	public static Iterator<UserCredentials> readExcelFile(String fileName) {
		List<UserCredentials> users=null;
		
		Row row;
		Cell emaiAddressCell;
		Cell passwordCell;
		UserCredentials user;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterate;
		XSSFWorkbook workBook = null;
		File xlsFileLoc = new File(System.getProperty("user.dir") + "//TestData//" + fileName);

		try {
			workBook = new XSSFWorkbook(xlsFileLoc);
			xssfSheet = workBook.getSheet("LoginData");
			users = new ArrayList<UserCredentials>();
			rowIterate = xssfSheet.rowIterator();
			rowIterate.next();
			while (rowIterate.hasNext()) {
				row = rowIterate.next();
				emaiAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new UserCredentials(emaiAddressCell.toString(), passwordCell.toString());
				users.add(user);
				System.out.println(emaiAddressCell.toString());
			}
			workBook.close();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return users.iterator();
	}

}
