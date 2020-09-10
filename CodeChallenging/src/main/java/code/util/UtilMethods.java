package code.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;

/**
 * 
 *   This class has mainly regular using method not to create multilple times 
 *   easy to utilize such as reading property file, getting connection,creating logger object
 *   
 *
 */

public class UtilMethods {

	private static org.apache.log4j.Logger logger = null;

	private static final String propertyFileName= "application.properties";
	
	private UtilMethods() {

	}

	/**
	 * @author praveen
	 * Logger object
	 *
	 */
	public static <T> org.apache.log4j.Logger getLoggerInstance(Class<T> className) {
		if (logger == null) {

			logger = org.apache.log4j.LogManager.getLogger(className);
			BasicConfigurator.configure();
		}
		return logger;
	}

	private static Connection conn = null;

	/**
	 * @author praveen
	 * JDBC connection object
	 *
	 */
	public static Connection getconnection() {
		try {

			if (conn == null) {

				Class.forName(getPropertiesFile("JDBC_DRIVER"));
				conn = DriverManager.getConnection(getPropertiesFile("DB_URL"),
						 getPropertiesFile("USERNAME"),  getPropertiesFile("PASSWORD"));

			}
			return conn;
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return conn;
		}
	}

	
	
	/**
	 * @author praveen
	 * 
	 * Closing the connection of jdbc
	 * 
	 * @param autoClose
	 * @return
	 */
	public static boolean closeConnections(AutoCloseable autoClose) {

		boolean isAutoClosed = false;

		try {
			if (autoClose != null) {
				autoClose.close();
				isAutoClosed = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAutoClosed;
	}

	/**
	 * Reading property file
	 *
	 */
	public static String getPropertiesFile(String key) throws IOException {

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream(propertyFileName)) {
			properties.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (String) properties.get(key);
	}

}
