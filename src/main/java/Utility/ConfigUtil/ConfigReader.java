package Utility.ConfigUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

@Slf4j
public class ConfigReader {


	@SneakyThrows
	public static String GetConfigInformation(String key)   {

		String configFileLocation = "src/test/resources/config.properties";
		try (FileInputStream propsInput = new FileInputStream(configFileLocation)) {
			Properties prop = new Properties();
			prop.load(propsInput);
			String keyValue =  prop.getProperty(key);
			if(keyValue == null){
				log.error(String.format("Config file doesn't contain the key [%s]",key));
				throw new RuntimeException();
			}
			return keyValue;
		} catch (FileNotFoundException e) {
			log.error(String.format("Configuration file not at location = %s", configFileLocation));
			throw new Exception(e);
		} catch (IOException e) {
			log.error("Error getting information please check key value");
			throw new RuntimeException(e);
		}
	}

}
