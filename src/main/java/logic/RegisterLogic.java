package logic;

import java.sql.SQLException;

import dao.RegisterDAO;
import model.User;
import util.PasswordUtil;

/**
 * ユーザーの新規登録に関する業務ロジックを提供するクラス。
 * ユーザー名やパスワードの検証、IDの発行、パスワードのハッシュ化、新規ユーザー登録を行う。
 */
public class RegisterLogic {
	
	/**
     * ユーザー名とパスワードの入力値を検証する。
     * 
     * @param name ユーザー名
     * @param plainPass パスワード（平文）
     * @return エラーメッセージ（入力値に問題がなければ null を返す）
     */
	public String validate(String name, String plainPass) {
		if (name == null || name.isEmpty() || plainPass == null || plainPass.isEmpty()) {
			return "ユーザー名とパスワードは必須です。";
		}
		if (plainPass.length() < 4) {
			return "パスワードは 4 文字以上にしてください。";
		}
		return null;
	}
	
	/**
     * 管理者または従業員の次のIDを取得する。
     * 
     * @param idType "admin" または "employee"
     * @return 次に発行するID文字列
     * @throws RuntimeException IDの上限に達した場合やID取得に失敗した場合にスローされる
     */
	private String getNextId(String idType) {
	    RegisterDAO dao = new RegisterDAO();
	    try {
	        String maxIdStr = idType.equals("admin") ? dao.findMaxAid() : dao.findMaxEid();
	        int nextId = 100000; // 初期値

	        if (maxIdStr != null && maxIdStr.matches("\\d{6}")) {
	            int maxId = Integer.parseInt(maxIdStr);
	            if (maxId >= 999999) {
	                throw new RuntimeException("IDが上限に達しました。");
	            }
	            nextId = maxId + 1;
	        }
	        return String.valueOf(nextId);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("ID取得に失敗しました", e);
	    }
	}

	/**
     * 次の管理者IDを取得する。
     * 
     * @return 管理者ID
     * @throws RuntimeException IDの上限に達した場合やID取得に失敗した場合にスローされる
     */
	public String getNextAid() {
	    return getNextId("admin");
	}
	
	/**
     * 次の従業員IDを取得する。
     * 
     * @return 従業員ID
     * @throws RuntimeException IDの上限に達した場合やID取得に失敗した場合にスローされる
     */
	public String getNextEid() {
	    return getNextId("employee");
	}

	/**
     * 平文のパスワードをハッシュ化する。
     * 
     * @param plainPass 平文のパスワード
     * @return ハッシュ化されたパスワード
     * @throws RuntimeException ハッシュ化に失敗した場合にスローされる
     */
	public String encryption(String plainPass) {

		try {
			String hashedPass = PasswordUtil.hashPassword(plainPass);
			return hashedPass;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("パスワードのハッシュ化に失敗しました", e);
		}
	}

	/**
     * 新規ユーザーを登録する。
     * 
     * @param user 登録対象のユーザー情報
     * @return 登録成功ならtrue、失敗ならfalse
     * @throws RuntimeException 登録処理で例外が発生した場合にスローされる
     */
	public boolean registerUser(User user) {
        
		RegisterDAO dao = new RegisterDAO();
		try{
			return dao.insertUser(user);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("新規登録に失敗しました。",e);
		}
	}

}
