package model;

import java.io.Serializable;

/**
 * ユーザー情報を表すモデルクラス。
 * ユーザー名、パスワード、権限情報を保持する。
 */
public class User implements Serializable{
	private Boolean authority;
	private String userCode;
	private String name;
	private String hashedPass;
	
	public User() {}
	
	public User(Boolean authority, String userCode,String hashedPass) {
		this.authority=authority;
		this.userCode=userCode;
		this.hashedPass=hashedPass;
	}
	
	public User(Boolean authority, String userCode,String name,String hashedPass) {
		this.authority=authority;
		this.userCode=userCode;
		this.name=name;
		this.hashedPass=hashedPass;
	}
	
	public Boolean getAuthority() {return authority;}
	public String getCode() {return userCode;}
	public String getName() {return name;}
	public String getHpass() {return hashedPass;}

}
