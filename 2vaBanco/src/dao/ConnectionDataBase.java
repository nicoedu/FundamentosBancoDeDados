package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

	private static final String URL_MYSQL = "jdbc:mysql://localhost/fdb_2ava";
		
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
		
	private static final String USER = "root";
	
	private static final String PASS = "";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}