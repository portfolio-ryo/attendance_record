package logic;

import java.sql.SQLException;

import dao.RegisterDAO;
import model.User;
import util.PasswordUtil;

/**
 * 登録処理を行うロジッククラス。
 */
public class RegisterLogic {
	/**
	 * ユーザー名とパスワードの入力値を検証
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
	 * 管理者IDの発行
	 */
	public String getNextAid() {
		RegisterDAO dao = new RegisterDAO();
		try {
	        String maxIdStr = dao.findMaxAid();  
	        int nextId = 100000;                // 初期値

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
	 * 従業員IDの発行
	 */
	public String getNextEid() {
		RegisterDAO dao = new RegisterDAO();
		try {
	        String maxIdStr = dao.findMaxEid();  
	        int nextId = 100000;                // 初期値

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
	 * パスワードをハッシュ化
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
	 * 新規登録
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
