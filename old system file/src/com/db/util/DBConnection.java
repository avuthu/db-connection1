package com.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection connection = null;

	public static Connection getConnection(String driver, String url, String user, String password) throws Exception {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
