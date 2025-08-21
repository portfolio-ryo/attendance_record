package model;

import java.io.Serializable;

/**
 * 勤怠記録を表すクラス。
 * <p>ユーザーコード、ユーザー名、勤務日、出勤時間、退勤時間を保持する。</P>

 */
public class Record implements Serializable{
	private String userCode;
	private String userName;
    private String workDate;
    private String clockIn;
    private String clockOut;

    /**
     * 全フィールドを指定して勤怠記録を作成するコンストラクタ。
     * 
     * @param userCode ユーザーコード
     * @param userName ユーザー名
     * @param workDate 勤務日
     * @param clockIn 出勤時間
     * @param clockOut 退勤時間
     */
    public Record(String userCode, String userName, String workDate, String clockIn, String clockOut) {
        this.userCode = userCode;
        this.userName = userName;
        this.workDate = workDate;
        this.clockIn = clockIn;
        this.clockOut = clockOut;
    }
    
    public String getUserCode() {return userCode;}
    public String getUserName() {return userName;}
    public String getWorkDate() {return workDate;}
    public String getClockIn() {return clockIn;}
    public String getClockOut() {return clockOut;}
}
