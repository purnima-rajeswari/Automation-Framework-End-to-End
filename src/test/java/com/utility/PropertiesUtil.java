package com.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Environments;

public class PropertiesUtil {

	public static String readProperty(Environments env,String property)  {
		File propQAFile =new File(System.getProperty("user.dir")+"//Config//"+env+".properties");
		FileReader fileReader = null;
		Properties properties=new Properties();
		try {
			fileReader = new FileReader(propQAFile);
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		String url=properties.getProperty(property.toUpperCase());
		return url;
	}

}
