package util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * パスワードのハッシュ化および検証を行うユーティリティクラス。
 * <p>
 * 登録時にパスワードを安全にハッシュ化し、ログイン時にその検証を行うために使用されます。
 * <p>
 * 使用ライブラリ：BCrypt（org.mindrot.jbcrypt.BCrypt）
 */
public class PasswordUtil {
	/**
     * プレーンなパスワードをBCryptでハッシュ化する。
     *
     * @param plainPassword プレーンテキストのパスワード
     * @return ハッシュ化されたパスワード
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // セキュリティ強度：12
    }

    /**
     * プレーンなパスワードとハッシュ済みパスワードを比較して、一致するかを検証する。
     *
     * @param plainPassword ユーザー入力されたプレーンテキストのパスワード
     * @param hashedPassword 保存済みのハッシュ化パスワード
     * @return パスワードが一致すれば true、一致しなければ false
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

