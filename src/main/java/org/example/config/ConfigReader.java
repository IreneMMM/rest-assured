package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	private static final Properties properties = new Properties();

	static {
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				throw new RuntimeException("Файл config.properties не найден в resources");
			}
			properties.load(input);
		} catch (IOException e) {
			throw new RuntimeException("Не удалось прочитать config.properties", e);
		}
	}

	private static String get(String key) {
		return properties.getProperty(key);
	}

	public static String getBaseUri() {
		return get("baseUri");
	}

	public static String getUsername() {
		return get("username");
	}

	public static String getPassword() {
		return get("password");
	}
}
