package com.ui.DataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.DataForTesting;
import com.ui.pojo.UserCredentials;
import com.utility.CSVReaderUtility;
import com.utility.ExcelDataUtility;

public class LoginDataProvider {
	@DataProvider(name="LoginTestDataProvider")
	public Iterator<Object[]> loginJSONDataProvider() throws FileNotFoundException {
		Gson gson=new Gson();
		File testDataFile= new File(System.getProperty("user.dir")+"\\TestData\\LoginData.json");
		FileReader fileReader= new FileReader(testDataFile);
		DataForTesting data= gson.fromJson(fileReader, DataForTesting.class);
		List<Object[]> dataToReturn= new ArrayList<Object[]>();
		for(UserCredentials user:data.getData()) {
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
	 }
	@DataProvider(name="LoginTestCSVDataProvider")
	public Iterator<UserCredentials> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("LoginData.csv");
	}
	@DataProvider(name="LoginTestXLDataProvider")
	public Iterator<UserCredentials> loginExcelDataProvider() {
		return ExcelDataUtility.readExcelFile("LoginData.xlsm");
	}
}


