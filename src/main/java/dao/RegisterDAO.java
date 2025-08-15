package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;
import model.User;

/**
 * ユーザー登録に関するデータベース操作を行うDAOクラス。
 */
public class RegisterDAO {

	private String jdbcUrl;
	private String dbUser;
	private String dbPass;

	public RegisterDAO() {
		Dotenv dotenv = Dotenv.configure()
				.directory("")
				.load();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("MySQL JDBC ドライバが見つかりません", e);
		}

		this.jdbcUrl = "jdbc:mysql://localhost:3306/attendance_record?serverTimezone=Asia/Tokyo";
		this.dbUser = dotenv.get("DB_USER");
		this.dbPass = dotenv.get("DB_PASS");

		if (dbUser == null || dbPass == null) {
			throw new IllegalStateException("環境変数が読み込めませんでした");
		}
	}

	/**
	 * 管理者テーブルから最大のID（ADMIN_CODE）を取得する。
	 */
	public String findMaxAid() throws SQLException {

		String sql = "SELECT MAX(ADMIN_CODE) FROM admin";

		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
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
	 */
	public String findMaxEid() throws SQLException {

		String sql = "SELECT MAX(EMPLOYEE_CODE) FROM employee";

		try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
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
	 */
	public boolean insertUser(User user) throws SQLException {

		String Asql = "INSERT INTO admin (ADMIN_CODE, NAME, PASS) VALUES (?, ?, ?)";
		String Esql = "INSERT INTO employee (EMPLOYEE_CODE, NAME, PASS) VALUES (?, ?, ?)";

		boolean admin = user.getAuthority();
		if (admin) {
			try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
				 PreparedStatement ps = conn.prepareStatement(Asql)) {

				ps.setString(1, user.getCode());
				ps.setString(2, user.getName());
				ps.setString(3, user.getHpass());

				int result = ps.executeUpdate();
				return result == 1;

			}
		} else {

			try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
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
