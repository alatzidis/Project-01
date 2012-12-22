package eu.quietroom.emp.entelligence.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class LogEntity {

	private ArrayList<LogRowEntity> rows = new ArrayList<LogRowEntity>();

	public ArrayList<LogRowEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<LogRowEntity> rows) {
		this.rows = rows;
	}

	public static void insertLogRowEntities(ArrayList<LogRowEntity> entities)
			throws SQLException {
		for (LogRowEntity dre : entities) {
			insertLogRowEntity(dre);
		}
	}

	public static void insertLogRowEntity(LogRowEntity lre) throws SQLException {
		String query = "";
		query += "insert into " + DBTools.TABLE_LOG + " values ('"
				+ lre.getEntity() + "','" + lre.getComplete() + "','"
				+ DateUtils.convertToString(lre.getDate(), "yyyy-MM-dd")
				+ "','" + lre.getError() + "','" + lre.getComments() + "')";
		QueryWrapper.executeInsertQuery(query);
	}

	private static String buildSelectClause() {
		return "select * from " + DBTools.TABLE_LOG;
	}

	private static String buildWhereDateClause(Date date) {
		return DBTools.TABLE_LOG_PREFIX + DBTools.DATE + " = '" + DateUtils.convertToString(date, "yyyy-MM-dd") + "'";
	}

	private static String buildWhereEntityClause(String entity) {
		return DBTools.TABLE_LOG_PREFIX + DBTools.ENTITY + " = '" + entity
				+ "'";
	}

	public static void updateLogRowEntity(LogRowEntity lre) throws SQLException {
		String query = "";
		query += "update " + DBTools.TABLE_LOG + " set log_complete = '"
				+ lre.getComplete() + "', log_error = '"
				+ lre.getError() + "', log_comments = '" + lre.getComments()
				+ "' where " + "log_entity = '" + lre.getEntity()
				+ "' and log_date ='"
				+ DateUtils.convertToString(lre.getDate(), "yyyy-MM-dd") + "'";
		QueryWrapper.executeUpdateQuery(query);
	}

	public static ArrayList<LogRowEntity> selectLogRowEntity(Date date)
			throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<LogRowEntity> results = new ArrayList<LogRowEntity>();
		while (rs.next()) {
			results.add(new LogRowEntity(rs.getString(1), rs.getInt(2), rs
					.getDate(3), rs.getInt(4), rs.getString(5)));
		}
		return results;
	}

	public static ArrayList<LogRowEntity> selectLogRowEntity(Date date,
			String entity) throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		query += " and " + buildWhereEntityClause(entity);
		QueryWrapper.executeSelectQuery(query);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<LogRowEntity> results = new ArrayList<LogRowEntity>();
		while (rs.next()) {
			results.add(new LogRowEntity(rs.getString(1), rs.getInt(2), rs
					.getDate(3), rs.getInt(4), rs.getString(5)));
		}
		return results;
	}

}
