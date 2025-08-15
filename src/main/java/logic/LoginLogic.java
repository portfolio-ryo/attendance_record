package logic;

import dao.LoginDAO;
import util.PasswordUtil;

/**
 * ログインに関するビジネスロジックを扱うクラス
 */
public class LoginLogic {
	/**
	 * 入力チェック
	 */
	public boolean isPasswordEmpty(String plainPass) {
		return plainPass==null || plainPass.isEmpty();
	}
	
	/**
	 * ユーザー検証を行う
	 */
	public boolean authenticate(String usercode,String plainPass, String action) {
		LoginDAO dao = new LoginDAO();
		String hashedpass = null;
		
		switch (action) {
		case "admin":
			hashedpass = dao.getAhashedPassword(usercode);
			break;
		case "attendance":
			hashedpass = dao.getEhashedPassword(usercode);
			break;
		default:
			System.out.println("actionの読み込みに失敗");
			return false;
		}
	    
	    if (hashedpass == null) {
	        return false; 
	    }
	    return PasswordUtil.checkPassword(plainPass, hashedpass);
	    
	}
}
