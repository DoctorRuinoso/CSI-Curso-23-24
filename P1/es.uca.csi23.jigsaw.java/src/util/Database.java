package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

	public static String getPropertiesUrl() { return "./db.properties"; }
	
	public static Connection Connection() throws SQLException, IOException {
		
		Properties properties = Config.Properties(getPropertiesUrl());
			
		return DriverManager.getConnection(
				properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"),
				properties.getProperty("jdbc.password"));
	}
	
	public static void LoadDriver() throws InstantiationException, IllegalAccessException, 
										   ClassNotFoundException, IOException, 
										   IllegalArgumentException, InvocationTargetException, 
										   NoSuchMethodException, SecurityException {
		
		Class.forName(Config.Properties(getPropertiesUrl()).getProperty(
				"jdbc.driverClassName")).getDeclaredConstructor().newInstance();
	}
	
	public static String String2Sql(
			String s, boolean bAddQuotes, boolean bAddWildcards) {
		s = s.replace("'", "''");
		if(bAddWildcards == true) s = "%" + s + "%";
		if(bAddQuotes == true) s = "'" + s + "'";
		return s;
	}
	
	public static int Boolean2Sql(boolean b) {
		if(b == false) { return 0; } else { return 1; }
	}
}