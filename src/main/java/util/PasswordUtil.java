package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	// パスワードをハッシュ化（登録時）
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // セキュリティ強度：12
    }

    // パスワードを検証（ログイン時）
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

