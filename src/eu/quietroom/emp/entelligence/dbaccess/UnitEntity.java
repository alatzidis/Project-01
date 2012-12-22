package eu.quietroom.emp.entelligence.dbaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import eu.quietroom.emp.utils.dateUtils.DateUtils;

public class UnitEntity {

	private ArrayList<UnitRowEntity> rows = new ArrayList<UnitRowEntity>();

	public ArrayList<UnitRowEntity> getRows() {
		return rows;
	}

	public void setRows(ArrayList<UnitRowEntity> rows) {
		this.rows = rows;
	}

	public static void insertUnitRowEntities(ArrayList<UnitRowEntity> entities)
			throws SQLException {
		for (UnitRowEntity dre : entities) {
			insertUnitRowEntity(dre);
		}
	}

	public static void insertUnitRowEntity(UnitRowEntity ure)
			throws SQLException {
		String query = "";
		query += "insert into " + DBTools.TABLE_UNIT + " values ('"
				+ DateUtils.convertToString(ure.getDate(), "yyyy-MM-dd")
				+ "','" + ure.getUnit() + "','" + ure.getMw() + "','"
				+ ure.getComments() + "')";
		QueryWrapper.executeInsertQuery(query);
	}

	private static String buildSelectClause() {
		return "select * from " + DBTools.TABLE_UNIT;
	}

	private static String buildWhereDateClause(Date date) {
		return DBTools.TABLE_UNIT_PREFIX + DBTools.DATE + " = '"
				+ DateUtils.convertToString(date, "yyyy-MM-dd") + "'";
	}

	private static String buildWhereGroupClause(String unit) {
		return DBTools.TABLE_UNIT_PREFIX + DBTools.GROUP + " = '" + unit + "'";
	}

	public static ArrayList<UnitRowEntity> selectUnitRowEntity(Date date)
			throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<UnitRowEntity> results = new ArrayList<UnitRowEntity>();
		while (rs.next()) {
			results.add(new UnitRowEntity(rs.getDate(1), rs.getString(2), rs
					.getDouble(3), rs.getString(4)));
		}
		return results;
	}

	public static ArrayList<UnitRowEntity> selectUnitRowEntity(Date date,
			String group) throws SQLException {
		String query = buildSelectClause();
		query += " where " + buildWhereDateClause(date);
		query += " and " + buildWhereGroupClause(group);
		ResultSet rs = QueryWrapper.executeSelectQuery(query);
		ArrayList<UnitRowEntity> results = new ArrayList<UnitRowEntity>();
		while (rs.next()) {
			results.add(new UnitRowEntity(rs.getDate(1), rs.getString(2), rs
					.getDouble(3), rs.getString(4)));
		}
		return results;
	}

}
