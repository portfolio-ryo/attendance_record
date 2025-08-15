package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.LoginLogic;

/**
 * ログイン処理を行うサーブレット。
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 認証または登録画面への遷移を行う。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String usercode = request.getParameter("usercode");
		String plainPass = request.getParameter("plainPass");
		String action = request.getParameter("action");
		
		LoginLogic loginLogic = new LoginLogic();

		if (loginLogic.isPasswordEmpty(plainPass)) {
			String message = URLEncoder.encode("パスワードが入力されていません", "UTF-8");
			response.sendRedirect("top.jsp?error="+message);
			return;
		}
		
		if (plainPass.length() < 4) {
		    String message = URLEncoder.encode("パスワードは4文字以上で入力してください", "UTF-8");
		    response.sendRedirect("top.jsp?error=" + message);
		    return;
		}


		//ログインチェック
		boolean loginSuccess = loginLogic.authenticate(usercode, plainPass, action);

		if (loginSuccess) {
			HttpSession session = request.getSession();
			session.setAttribute("usercode", usercode);

			switch (action) {
			case "attendance":
				request.getRequestDispatcher("/WEB-INF/jsp/employee.jsp").forward(request, response);
				break;
			case "admin":
				request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp").forward(request, response);
				break;
			default:
				String error = URLEncoder.encode("ログインに失敗しました。", "UTF-8");
				response.sendRedirect("top.jsp?error="+error);
				return;
			}
		} else {
			String error = URLEncoder.encode("ユーザー情報が登録されていません。", "UTF-8");
			response.sendRedirect("top.jsp?error="+error);
			return;
		}
	}
}
