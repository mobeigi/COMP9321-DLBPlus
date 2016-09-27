package edu.unsw.comp9321;

import java.sql.*;

/**
 * Database helper class which abstracts database calls.
 */

public class DBHelper {
	private static final String HOST = "localhost";
	private static final Integer PORT = 5432;
	private static final String dbName = "dlbplus";
	private static final String dbUser = "postgres";
	private static final String dbPass = "password";

	private Connection dbConn = null;
	private boolean dbConnStatus = false;

	/**
	 * Initiate connection to DB
	 */
	public boolean init() {
		try {
			Class.forName("org.postgresql.Driver");
			dbConn = DriverManager
							.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/" + dbName,
											dbUser, dbPass);
			dbConnStatus = true;
		} catch (Exception e) {
			dbConnStatus = false;
		}

		return dbConnStatus;
	}

	public void test() {
		if (!dbConnStatus)
			return;

		try {
			Statement stmt = null;
			dbConn.setAutoCommit(false);
			stmt = dbConn.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT authors FROM publications;" );
			while ( rs.next() ) {
				String authors = rs.getString("authors");
				System.out.println( "Author = " + authors );
			}
			rs.close();
			stmt.close();
		}
		 catch (SQLException e) {
			return;
		}
	}

}
