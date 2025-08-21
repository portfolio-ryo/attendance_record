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
     * 出勤の打刻処理を行う。
     *
     * @param usercode 打刻するユーザーのコード
     * @param timestamp 打刻の日時（通常は現在時刻）
     * @return 結果メッセージ（例：打刻成功や失敗理由）
     */
	public String clockIn(String usercode, LocalDateTime timestamp) {
		AttendanceDAO dao = new AttendanceDAO();
		return dao.attendance(usercode, timestamp);
	}
	
	/**
     * 退勤の打刻処理を行う。
     *
     * @param usercode 打刻するユーザーのコード
     * @param timestamp 打刻の日時（通常は現在時刻）
     * @return 結果メッセージ（例：打刻成功や失敗理由）
     */
	public String clockOut(String usercode, LocalDateTime timestamp) {
		AttendanceDAO dao = new AttendanceDAO();
		return dao.leave(usercode, timestamp);
	}
	
	/**
     * 全ユーザーの出勤・退勤履歴を取得する。
     *
     * @return 勤務記録（Record）のリスト
     * @throws SQLException データベース接続やクエリの実行に失敗した場合にスローされる
     */
	public List<Record> getRecordList() throws SQLException {
        RecordDAO dao = new RecordDAO();
        return dao.findAll();
    }

}
