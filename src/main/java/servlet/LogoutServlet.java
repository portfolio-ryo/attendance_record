package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * ログアウト処理を行うサーブレット。
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * セッションを破棄してトップ画面に移動する。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション破棄
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            
            String message = URLEncoder.encode("ログアウトしました", "UTF-8");
            response.sendRedirect("top.jsp?message=" + message);
        }
	}

}
