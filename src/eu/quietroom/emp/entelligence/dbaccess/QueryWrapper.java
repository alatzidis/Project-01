package eu.quietroom.emp.entelligence.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryWrapper {

	private static void executeStatement(String query) throws SQLException {
		Statement st = DBTools.con.createStatement();
		st.execute(query);
	}

	public static void executeUpdateQuery(String query) throws SQLException {
		executeStatement(query);
	}

	public static void executeInsertQuery(String query) throws SQLException {
		executeStatement(query);
	}

	public static ResultSet executeSelectQuery(String query)
			throws SQLException {
		Statement st = DBTools.con.createStatement();
		return st.executeQuery(query);
	}

}