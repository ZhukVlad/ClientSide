package comarch.mediation.ClientSide.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {
	private static final String PROPERTIES_PATH = "c:/mediation/client/";
	private static final String PROPERTIES_EXT = ".properties";
	private static final String CANNOT_LOAD_DATA = "Cannot load data ";
	
	public static final String MEDIATION_FILE = "mediation";
	public static final String CONNECTION_CHECK = "connection_check";
	
	public static Long id = 1L;
	public static Boolean isConnected = Boolean.FALSE;
	
	private Properties properties;
	
	public ConfigUtil() {
	}
	
	public Map<String, Object> getConfig(String fileName) {
		Map<String, Object> config = null;
		try (InputStream inputStream = new FileInputStream(new File(PROPERTIES_PATH + fileName + PROPERTIES_EXT))) {
			if (inputStream != null) {
				properties = new Properties();
				config = new HashMap<>();
				properties.load(inputStream);
				for (String name : properties.stringPropertyNames()) {
					config.put(name, properties.getProperty(name));
				}
			}
		} catch (IOException e) {
			LoggerUtil.error(CANNOT_LOAD_DATA, e);
		}
		return config;
	}
}
