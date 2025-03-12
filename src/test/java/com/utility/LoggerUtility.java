package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {
// Global configuration or global setup for logger
	//private static Logger logger;// we r moving this statement in to getLogger method to make it thread safe

	// in singleton class a constructor can be private
	private LoggerUtility() {

	}

	public static Logger getLogger(Class<?> clazz) {
		Logger logger=null;
		if (logger == null) {
			logger = LogManager.getLogger(clazz);
		}
		return logger;
	}

}
