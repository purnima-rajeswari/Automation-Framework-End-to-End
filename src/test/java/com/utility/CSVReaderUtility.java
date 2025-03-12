package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.UserCredentials;

public class CSVReaderUtility {

	public static Iterator<UserCredentials> readCSVFile(String fileName) {
		FileReader readLoginData = null;
		File csvFileLoc = new File(System.getProperty("user.dir") + "//TestData//"+fileName);
		CSVReader csvReader = null;
		String[] rowLine=null;//combo of email and pwd.
		List<UserCredentials> userList=null;;
		
		try {
			readLoginData = new FileReader(csvFileLoc);
			csvReader = new CSVReader(readLoginData);
			userList=new ArrayList<UserCredentials>();
			UserCredentials user=null;
			csvReader.readNext();
			while((rowLine = csvReader.readNext())!=null){
				user=new UserCredentials(rowLine[0],rowLine[1]);
				userList.add(user);
			}
			for(UserCredentials userData : userList) {
				System.out.println(userData);
			}
		} catch (CsvValidationException | IOException e) {

			e.printStackTrace();
		}
		return userList.iterator();
	}
}
