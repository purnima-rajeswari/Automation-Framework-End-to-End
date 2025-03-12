package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.constants.Environments.*;

import com.constants.Environments;
import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;


public class JSONUtility {
	public static Environment readJSON(Environments env)  {
	///public static void main(String[] args) {
		Gson gson = new Gson();
		File jsonFile=new File(System.getProperty("user.dir")+"\\Config\\config.json");
		System.out.println(System.getProperty("user.dir")+"\\Config\\config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		Config config=gson.fromJson(fileReader, Config.class);
		Environment environment=config.getEnvironments().get("QA");
		System.out.println(environment.getUrl());
		return environment;
	}
}
