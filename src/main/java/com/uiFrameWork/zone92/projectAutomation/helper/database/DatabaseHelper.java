package com.uiFrameWork.zone92.projectAutomation.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import com.uiFrameWork.zone92.projectAutomation.helper.logger.LoggerHelper;

public class DatabaseHelper {
	
	private static Logger log = LoggerHelper.getLogger(DatabaseHelper.class);
	private static String url = "jdbc:mysql://localhost/thusitha";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "1qaz2wsxT";
	private static Connection connection;

	private static DatabaseHelper instance = null;
	
	
	public DatabaseHelper() {
		connection = getSingleInstanceConnection();
	}
	
	public static DatabaseHelper getInstance() {
		if(instance == null) {
			instance = new DatabaseHelper();
		}
		return instance;
	}
	
	public Connection getSingleInstanceConnection() {
		
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName, password);
				if(connection!= null) {
					log.info("Connected to dataBase..");
				}
			}
			catch (SQLException e) {
				log.error("Failed to create Data base connection.."+e);
			}
		}
		catch (ClassNotFoundException e) {
				log.info("Driver not found.."+e);
		}
		return connection;
	} 
	
	public Connection getConnection() {
		return connection;
		
	}
	
	public static ResultSet getResultSet(String dbQuery) {
		instance = DatabaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query: " +dbQuery);		
			try {
				Statement stmt = connection.createStatement();
				ResultSet result = stmt.executeQuery(dbQuery);
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		return null;		
	}
	
}
