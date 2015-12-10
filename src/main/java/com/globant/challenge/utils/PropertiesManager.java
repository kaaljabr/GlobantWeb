package com.globant.challenge.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesManager {

	private static Logger log = LoggerFactory.getLogger(PropertiesManager.class);

	private volatile static PropertiesManager _instance;
	private static Properties properties = null;

	private static final String PROPERTY_FILE = "config.properties";

	private PropertiesManager() {
		loadProperties(PROPERTY_FILE);

	}

	public static PropertiesManager getInstance() {
		if (_instance == null) {
			synchronized (PropertiesManager.class) {
				if (PropertiesManager._instance == null) {
					PropertiesManager._instance = new PropertiesManager();
				}
			}
		}
		return _instance;
	}

	private void loadProperties(String fileName) {
		try {
			properties = new Properties();
			InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("config/" + fileName);
			InputStreamReader reader = new InputStreamReader(resourceAsStream, "UTF8");
			properties.load(reader);
			log.warn("----> INITIALIZED ... PropertiesManager <----");
		} catch (FileNotFoundException e) {
			log.error("Error in loading property file", e);

		} catch (IOException e) {
			log.error("Error in loading property file", e);
		}

	}

	public String getProperty(String key) {
		Object object = properties.get(key);
		return object.toString();
	}

	public int getPropertyInt(String key) {
		Object object = properties.get(key);
		int parseInt = Integer.parseInt(object.toString());
		return parseInt;
	}

	public boolean getPropertyBoolean(String key) {
		Object object = properties.get(key);
		boolean parseBoolean = Boolean.parseBoolean(object.toString());
		return parseBoolean;
	}

}
