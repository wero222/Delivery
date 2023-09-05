package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DB {
	
	// Singleton pattern
	// -----------------------
	// Avere un metodo che torna la Connection se gà ce l'ha
	// altrimenti la crea e la ritorna
	
	private static Connection connection;
	
	public static Connection getDb() throws Exception {
		if(connection == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/delivery",
				"root",
				"1234"
			);
		}
		return connection;
	}
	
	public static Statement getStmt() throws Exception {
		return getDb().createStatement();
	}
	
	public static PreparedStatement getPreparedStmt(String query) throws Exception {
		return getDb().prepareStatement(query);
	}
	
	public static PreparedStatement getPreparedStmtWithId(String query) throws Exception {
		return getDb().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}

}
