package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * ログイン情報のDBアクセスを担当するDAOクラス。
 */
public class LoginDAO {
	private String jdbcUrl;
	private String dbUser;
	private String dbPass;
	
	public LoginDAO() {
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
     * 指定した管理者コードの参照。
     */
	public String getAhashedPassword(String usercode) {
	    String sql = "SELECT PASS FROM admin WHERE ADMIN_CODE = ?";

	    try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, usercode);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getString("PASS"); 
	        } else {
	            return null; 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	/**
     * 指定した従業員コードの参照。
     */
	public String getEhashedPassword(String usercode) {
	    String sql = "SELECT PASS FROM employee WHERE EMPLOYEE_CODE = ?";

	    try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, usercode);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getString("PASS"); 
	        } else {
	            return null; 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

}
