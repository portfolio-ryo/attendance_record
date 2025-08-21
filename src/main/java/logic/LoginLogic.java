package logic;

import dao.LoginDAO;
import util.PasswordUtil;

/**
 * ログインに関するビジネスロジックを扱うクラス
 */
public class LoginLogic {
	
	/**
     * パスワードが未入力かどうかを判定する。
     *
     * @param plainPass 入力されたプレーンなパスワード
     * @return true: 未入力（null または 空文字）、false: 入力あり
     */
	public boolean isPasswordEmpty(String plainPass) {
		return plainPass==null || plainPass.isEmpty();
	}
	
	/**
     * ユーザーコードとパスワードで認証を行う。
     *
     * @param usercode ユーザーコード（従業員ID または 管理者ID）
     * @param plainPass 入力されたパスワード（プレーンテキスト）
     * @param action "admin"：管理者ログイン、"attendance"：従業員ログイン
     * @return 認証成功：true、失敗：false
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
