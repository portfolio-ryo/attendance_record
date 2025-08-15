package model;

import java.io.Serializable;

/**
 * 勤怠記録を表すクラス。
 */
public class Record implements Serializable{
	private String userCode;
	private String userName;
    private String workDate;
    private String clockIn;
    private String clockOut;

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
