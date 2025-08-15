package servlet;

import java.security.MessageDigest;

/**
 * 文字列をSHA-256でハッシュ化するユーティリティクラス。
 */
public class SimpleHash {
	
	/**
     * 指定された文字列をSHA-256でハッシュ化して16進数文字列として返します。
     */
	public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("ハッシュ化に失敗", e);
        }
    }


}
