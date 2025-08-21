package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Record;
import util.DBUtil;

/**
 * 出勤・退勤記録をデータベースから取得するDAOクラス。
 */
public class RecordDAO {

	public RecordDAO() {
	}
	
	/**
	 * すべての出勤・退勤レコードを取得する。
	 * 従業員テーブルと結合し、最新日付順にソートして返す。
	 * 
	 * @return 出勤・退勤履歴のリスト
     * @throws SQLException データベースアクセス時にエラーが発生した場合
	 */
	public List<Record> findAll() throws SQLException{
        List<Record> list = new ArrayList<>();
        
        String sql = "SELECT  e.EMPLOYEE_CODE, e.NAME, r.WORK_DATE, r.CLOCK_IN, r.CLOCK_OUT " +
                "FROM record r " +
                "JOIN employee e ON r.EMPLOYEE_ID = e.EMPLOYEE_CODE " +
                "ORDER BY r.WORK_DATE DESC";
        
        try (Connection conn = DBUtil.getConnection();
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
