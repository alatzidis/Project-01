package eu.quietroom.emp.entelligence.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class DataEntity {

	private ArrayList<DataRowEntity> rows = new ArrayList<DataRowEntity>();

	public ArrayList<DataRowEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<DataRowEntity> rows) {
		this.rows = rows;
	}

	public static void insertDataRowEntities(ArrayList<DataRowEntity> entities)
			throws SQLException {
		for (DataRowEntity dre : entities) {
			insertDataRowEntity(dre);
		}
	}

	public static void insertDataRowEntity(DataRowEntity dre)
			throws SQLException {
		String query = "";
		query += "insert into " + DBTools.TABLE_DATA + " values ('"
				+ DateUtils.convertToString(dre.getDate(), "yyyy-MM-dd")
				+ "','" + dre.getGroup() + "','" + dre.getStep() + "','"
				+ dre.getData() + "','" + dre.getMetadata() + "')";
		QueryWrapper.executeInsertQuery(query);
	}

	private static String buildSelectClause() {
		return "select * from " + DBTools.TABLE_DATA;
	}

	private static String buildWhereDateClause(Date date) {
		return DBTools.TABLE_DATA_PREFIX + DBTools.DATE + " = '"
				+ DateUtils.convertToString(date, "yyyy-MM-dd") + "'";
	}

	private static String buildWhereGroupClause(String group) {
		return DBTools.TABLE_DATA_PREFIX + DBTools.GROUP + " = '" + group + "'";
	}

	private static String buildLazyWhereGroupClause(String group) {
		return DBTools.TABLE_DATA_PREFIX + DBTools.GROUP + " like '" + group
				+ "%'";
	}

	private static String buildWhereStepClause(int step) {
		return DBTools.TABLE_DATA_PREFIX + DBTools.STEP + " = '" + step + "'";
	}

	public static ArrayList<DataRowEntity> selectDataRowEntity(Date date)
			throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<DataRowEntity> results = new ArrayList<DataRowEntity>();
		while (rs.next()) {
			results.add(new DataRowEntity(rs.getDate(1), rs.getString(2), rs
					.getInt(3), rs.getDouble(4), rs.getString(5)));
		}
		return results;
	}

	public static ArrayList<DataRowEntity> selectDataRowEntity(Date date,
			String group) throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		query += " and " + buildWhereGroupClause(group);
		QueryWrapper.executeSelectQuery(query);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<DataRowEntity> results = new ArrayList<DataRowEntity>();
		while (rs.next()) {
			results.add(new DataRowEntity(rs.getDate(1), rs.getString(2), rs
					.getInt(3), rs.getDouble(4), rs.getString(5)));
		}
		return results;
	}

	public static ArrayList<DataRowEntity> selectDataRowEntity(Date date,
			String group, int step) throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		query += " and " + buildWhereGroupClause(group);
		query += " and " + buildWhereStepClause(step);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<DataRowEntity> results = new ArrayList<DataRowEntity>();
		while (rs.next()) {
			results.add(new DataRowEntity(rs.getDate(1), rs.getString(2), rs
					.getInt(3), rs.getDouble(4), rs.getString(5)));
		}
		return results;
	}

	public static ArrayList<DataRowEntity> selectLazyDataRowEntity(Date date,
			String group, int step) throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		query += " and " + buildLazyWhereGroupClause(group);
		query += " and " + buildWhereStepClause(step);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<DataRowEntity> results = new ArrayList<DataRowEntity>();
		while (rs.next()) {
			results.add(new DataRowEntity(rs.getDate(1), rs.getString(2), rs
					.getInt(3), rs.getDouble(4), rs.getString(5)));
		}
		return results;
	}

}
