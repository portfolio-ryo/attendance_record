package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dao.AttendanceDAO;
import dao.RecordDAO;
import model.Record;

/**
 * 出勤・退勤の打刻処理を行うロジッククラス。
 */
public class RecordLogic {
	
	/**
     * 出勤打刻を行う。
     */
	public String clockIn(String usercode, LocalDateTime timestamp) {
		AttendanceDAO dao = new AttendanceDAO();
		return dao.attendance(usercode, timestamp);
	}
	
	 /**
     * 退勤打刻を行う。
     */
	public String clockOut(String usercode, LocalDateTime timestamp) {
		AttendanceDAO dao = new AttendanceDAO();
		return dao.leave(usercode, timestamp);
	}
	
	/**
	 * 勤務履歴一覧を表示
	 */
	public List<Record> getRecordList() throws SQLException {
        RecordDAO dao = new RecordDAO();
        return dao.findAll();
    }

}
