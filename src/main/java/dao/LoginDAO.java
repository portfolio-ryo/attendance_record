package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

/**
 * ログイン情報のDBアクセスを担当するDAOクラス。
 * 管理者・従業員それぞれのパスワードを取得する。
 */
public class LoginDAO {

    public LoginDAO() {}

    /**
     * 指定された管理者コードに対応するハッシュ化パスワードを取得する。
     *
     * @param usercode 管理者コード
     * @return ハッシュ化されたパスワード、該当がない場合は null
     */
    public String getAhashedPassword(String usercode) {
        return getPassword("admin", "ADMIN_CODE", usercode);
    }

    /**
     * 指定された従業員コードに対応するハッシュ化パスワードを取得する。
     *
     * @param usercode 従業員コード
     * @return ハッシュ化されたパスワード、該当がない場合は null
     */
    public String getEhashedPassword(String usercode) {
        return getPassword("employee", "EMPLOYEE_CODE", usercode);
    }

    /**
     * 指定されたテーブル・列からパスワードを取得する共通メソッド。
     *
     * @param tableName テーブル名（例: "admin", "employee"）
     * @param columnName 主キー列名（例: "ADMIN_CODE", "EMPLOYEE_CODE"）
     * @param usercode ユーザーコード
     * @return ハッシュ化されたパスワード、または null
     */
    private String getPassword(String tableName, String columnName, String usercode) {
        String sql = "SELECT PASS FROM " + tableName + " WHERE " + columnName + " = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usercode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("PASS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
