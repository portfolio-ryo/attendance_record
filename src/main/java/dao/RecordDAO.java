package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import model.Record;

/**
 * 出勤・退勤記録をデータベースから取得するDAOクラス。
 */
public class RecordDAO {

	private String jdbcUrl;
	private String dbUser;
	private String dbPass;

	public RecordDAO() {
		Dotenv dotenv = Dotenv.configure()
				.directory("")
				.load();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("MySQL JDBC ドライバが見つかりません", e);
		}

		this.jdbcUrl = "jdbc:mysql://localhost:3306/attendance_record?serverTimezone=Asia/Tokyo";
		this.dbUser = dotenv.get("DB_USER");
		this.dbPass = dotenv.get("DB_PASS");

		if (dbUser == null || dbPass == null) {
			throw new IllegalStateException("環境変数が読み込めませんでした");
		}
	}
	
	/**
	 * すべての出勤・退勤レコードを取得する。
	 * 従業員テーブルと結合し、最新日付順にソートして返す。
	 */
	public List<Record> findAll() throws SQLException{
        List<Record> list = new ArrayList<>();
        
        String sql = "SELECT  e.EMPLOYEE_CODE, e.NAME, r.WORK_DATE, r.CLOCK_IN, r.CLOCK_OUT " +
                "FROM record r " +
                "JOIN employee e ON r.EMPLOYEE_ID = e.EMPLOYEE_CODE " +
                "ORDER BY r.WORK_DATE DESC";
        
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
        	 PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
            	
            	String userCode = rs.getString("EMPLOYEE_CODE");
            	String userName = rs.getString("NAME");
                String workDate = rs.getString("WORK_DATE");
                String clockIn = rs.getString("CLOCK_IN");
                String clockOut = rs.getString("CLOCK_OUT");

                Record rec = new Record(userCode, userName, workDate, clockIn, clockOut);
                list.add(rec);
            }
        }         
        return list;
    }
	
}
