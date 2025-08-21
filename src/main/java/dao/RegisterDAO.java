package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

/**
 * ユーザー登録に関するデータベース操作を行うDAOクラス。
 */
public class RegisterDAO {

	public RegisterDAO() {
	}

	/**
	 * 管理者テーブルから最大のID（ADMIN_CODE）を取得する。
	 * 
	 * @return 最大の管理者ID（6桁の文字列）、存在しない場合は null
     * @throws SQLException データベースアクセスエラーが発生した場合
	 */
	public String findMaxAid() throws SQLException {

		String sql = "SELECT MAX(ADMIN_CODE) FROM admin";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getString(1);
			}
		}

		return null;
	}

	/**
	 * 従業員テーブルから最大のID（EMPLOYEE_CODE）を取得する。
	 * 
	 * @return 最大の従業員ID（6桁の文字列）、存在しない場合は null
     * @throws SQLException データベースアクセスエラーが発生した場合
	 */
	public String findMaxEid() throws SQLException {

		String sql = "SELECT MAX(EMPLOYEE_CODE) FROM employee";

		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				return rs.getString(1);
			}
		}

		return null;
	}

	/**
	 * ユーザー情報を管理者または従業員テーブルに登録する。
	 * 
	 * @param user 登録対象のユーザー情報
     * @return 登録成功: true、失敗: false
     * @throws SQLException データベースアクセスエラーが発生した場合
	 */
	public boolean insertUser(User user) throws SQLException {

		String Asql = "INSERT INTO admin (ADMIN_CODE, NAME, PASS) VALUES (?, ?, ?)";
		String Esql = "INSERT INTO employee (EMPLOYEE_CODE, NAME, PASS) VALUES (?, ?, ?)";

		boolean admin = user.getAuthority();
		if (admin) {
			try (Connection conn = DBUtil.getConnection();
				 PreparedStatement ps = conn.prepareStatement(Asql)) {

				ps.setString(1, user.getCode());
				ps.setString(2, user.getName());
				ps.setString(3, user.getHpass());

				int result = ps.executeUpdate();
				return result == 1;

			}
		} else {

			try (Connection conn = DBUtil.getConnection();
					PreparedStatement ps = conn.prepareStatement(Esql)) {

				ps.setString(1, user.getCode());
				ps.setString(2, user.getName());
				ps.setString(3, user.getHpass());

				int result = ps.executeUpdate();
				return result == 1;

			}
		}
	}
}
