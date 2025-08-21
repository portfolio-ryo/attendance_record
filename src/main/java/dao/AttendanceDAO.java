package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import util.DBUtil;

/**
 * 勤怠情報のDB操作を行うDAOクラス。
 */
public class AttendanceDAO {

	public AttendanceDAO() {
	}

	/**
	* 指定ユーザーの指定日時で勤怠打刻を行う。
	* <ul>
	* <li>同一日で出勤記録がなければ出勤時間を新規登録</li>
	* <li>出勤記録があれば退勤時間を更新</li>
	* </ul>
	* 
	* @param usercode 出勤を行うユーザーのコード（従業員ID）
	 * @param now 出勤ボタンが押された日時（現在日時）
	 * @return 出勤処理の結果メッセージ
	*/
	public String attendance(String usercode, LocalDateTime now) {
	    LocalDate date = now.toLocalDate();
	    LocalTime time = now.toLocalTime();

	    String selectSql = "SELECT CLOCK_IN FROM record WHERE EMPLOYEE_ID = ? AND WORK_DATE = ?";
	    String insertSql = "INSERT INTO record (EMPLOYEE_ID, WORK_DATE, CLOCK_IN) VALUES (?, ?, ?)";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(selectSql)) {

	        ps.setString(1, usercode);
	        ps.setDate(2, Date.valueOf(date));

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                // 出勤時間がすでにある場合は処理済みとみなす
	                if (rs.getTime("CLOCK_IN") != null) {
	                    return "出勤処理は済んでいます。";
	                }
	            } 

	            // 出勤時間がないなら新規登録
	            try (PreparedStatement insertPs = conn.prepareStatement(insertSql)) {
	                insertPs.setString(1, usercode);
	                insertPs.setDate(2, Date.valueOf(date));
	                insertPs.setTime(3, Time.valueOf(time));

	                int result = insertPs.executeUpdate();
	                return result > 0 ? "出勤処理が完了しました。" : "出勤処理に失敗しました。";
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "データベースエラーが発生しました。";
	    }
	}

	

	/**
	 * 退勤打刻を登録する。
	 * <ul>
	 *   <li>該当日出勤済みなら退勤時間を更新。</li>
	 *   <li>退勤済みならメッセージを返す。</li>
	 *   <li>出勤が未記録ならエラーメッセージを返す。</li>
	 * </ul>
	 * 
	 * @param usercode 退勤を行うユーザーのコード（従業員ID）
	 * @param now 退勤ボタンが押された日時（現在日時）
	 * @return 退勤処理の結果メッセージ
	 */
	public String leave(String usercode, LocalDateTime now) {

	    LocalDate date = now.toLocalDate();
	    LocalTime time = now.toLocalTime();

	    String selectSql = "SELECT * FROM record WHERE EMPLOYEE_ID = ? AND WORK_DATE = ?";
	    String updateSql = "UPDATE record SET CLOCK_OUT = ? WHERE EMPLOYEE_ID = ? AND WORK_DATE = ?";

	    try (Connection conn = DBUtil.getConnection();
	         PreparedStatement ps = conn.prepareStatement(selectSql)) {

	        ps.setString(1, usercode);
	        ps.setDate(2, Date.valueOf(date));

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Time clockOut = rs.getTime("CLOCK_OUT");
	                if (clockOut != null) {
	                    return "退勤処理は済んでいます。"; 
	                }

	                try (PreparedStatement updatePs = conn.prepareStatement(updateSql)) {
	                    updatePs.setTime(1, Time.valueOf(time));
	                    updatePs.setString(2, usercode);
	                    updatePs.setDate(3, Date.valueOf(date));

	                    int result = updatePs.executeUpdate();
	                    return result > 0 ? "退勤処理が完了しました。":"退勤処理に失敗しました。";
	                }
	            } else {
	                return "出勤ボタンが押されていません。"; 
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "データベースエラーが発生しました。";
	    }
	}
}