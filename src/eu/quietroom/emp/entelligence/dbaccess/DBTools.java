package eu.quietroom.emp.entelligence.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTools {

	//static data
	static Connection con;
	static final int DATA_COLUMNS = 25;
	static final int METADATA_COLUMS = 4;
	static final String TABLE_DATA = Messages.getString("DBTools.0"); //$NON-NLS-1$
	static final String TABLE_DATA_PREFIX = Messages.getString("DBTools.1"); //$NON-NLS-1$
	static final String TABLE_UNIT = Messages.getString("DBTools.2"); //$NON-NLS-1$
	static final String TABLE_UNIT_PREFIX = Messages.getString("DBTools.3"); //$NON-NLS-1$
	static final String TABLE_LOG = Messages.getString("DBTools.4"); //$NON-NLS-1$
	static final String TABLE_LOG_PREFIX = Messages.getString("DBTools.5"); //$NON-NLS-1$
	static final String DATE = Messages.getString("DBTools.6"); //$NON-NLS-1$
	static final String GROUP = Messages.getString("DBTools.7"); //$NON-NLS-1$
	static final String COMPLETE = Messages.getString("DBTools.8"); //$NON-NLS-1$
	static final String ENTITY = Messages.getString("DBTools.9"); //$NON-NLS-1$
	static final String ERROR = Messages.getString("DBTools.10"); //$NON-NLS-1$
	static final String COMMENTS = Messages.getString("DBTools.11"); //$NON-NLS-1$
	static final String STEP = Messages.getString("DBTools.12"); //$NON-NLS-1$
	static final String DATA = Messages.getString("DBTools.13"); //$NON-NLS-1$
	static final String METADATA = Messages.getString("DBTools.14"); //$NON-NLS-1$
	static final String SELECT_DATA_STAR = Messages.getString("DBTools.15") //$NON-NLS-1$
			+ DBTools.TABLE_DATA;
	static final String DBURL = Messages.getString("DBTools.16"); //$NON-NLS-1$
	static final String DBCLASS = Messages.getString("DBTools.17"); //$NON-NLS-1$
	static final String DBUSER = Messages.getString("DBTools.18"); //$NON-NLS-1$
	static final String DBPASS = Messages.getString("DBTools.19"); //$NON-NLS-1$
	
	
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			DBTools myTool = new DBTools();
			myTool.startTool();
			myTool.test();
			myTool.closeDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void startTool() throws Exception {
		
		
		Class.forName(DBCLASS);
		con = DriverManager.getConnection(DBURL, DBUSER , DBPASS);
	}

	private void closeDB() throws SQLException {
		con.close();
	}

	private void test() throws SQLException {
		ArrayList<LogRowEntity> entities = new ArrayList<LogRowEntity>();
		LogRowEntity lre = new LogRowEntity("test",0,new java.util.Date(),0,"no comment!");
		entities.add(lre);
		LogEntity.insertLogRowEntities(entities);
		lre.setComplete(1);
		LogEntity.updateLogRowEntity(lre);
		lre = null;
		ArrayList<LogRowEntity> res = LogEntity.selectLogRowEntity(new java.util.Date());
		for(LogRowEntity l : res) {
			lre = l;
			System.out.println(lre.getComments());
		}
		
		//LogEntity test = LogEntity.
		/*String query = Messages.getString("DBTools.20") + DBTools.TABLE_DATA; //$NON-NLS-1$
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getDate(1) + Messages.getString("DBTools.21") + rs.getString(2)); //$NON-NLS-1$
		}*/
	}

}
