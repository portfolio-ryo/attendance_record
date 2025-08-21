package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベース接続用のユーティリティクラス。
 * <p>
 * 環境変数（DB_USER, DB_PASS）から接続情報を読み込み、MySQLデータベースへの接続を提供します。
 * </p>
 * <p>
 * ドライバのロードはクラスロード時に一度だけ行われます。
 * </p>
 */
public class DBUtil {
    private static String jdbcUrl = "jdbc:mysql://localhost:3306/attendance_record?serverTimezone=Asia/Tokyo";
    private static String dbUser;
    private static String dbPass;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Loaded");
            // dotenvから環境変数読み込みなど
            io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure().directory("").load();
            dbUser = dotenv.get("DB_USER");
            dbPass = dotenv.get("DB_PASS");
            if (dbUser == null || dbPass == null) {
                throw new IllegalStateException("環境変数が読み込めませんでした");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBCドライバのロードに失敗しました", e);
        }
    }

    /**
     * データベース接続を取得します。
     *
     * @return データベース接続
     * @throws SQLException 接続エラーが発生した場合
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
    }
}
