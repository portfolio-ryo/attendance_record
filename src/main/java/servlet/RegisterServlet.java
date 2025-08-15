package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.RegisterLogic;
import model.User;

/**
 * ユーザーの新規登録をするクラス。
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * ユーザー登録フォームからPOSTリクエストを処理。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String plainPass =request.getParameter("plainPass");
		String isAdminParam = request.getParameter("isAdmin");
		boolean isAdmin = "true".equals(isAdminParam);
	    
		// 初回アクセス（未入力）の場合 → そのまま表示して終了
		if (name == null && plainPass == null) {
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	        return;
		}
		
		RegisterLogic logic=new RegisterLogic();
		String error = logic.validate(name,plainPass);
		
		if (error !=null) {
			request.setAttribute("error", error);
            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
            return;
		}
		
		String userCode;
		
		if (isAdmin) {
			try {
				userCode = logic.getNextAid();
			}catch(RuntimeException e) {
				request.setAttribute("error", e.getMessage());
		        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		        return;
			}
		}else {
			try {
				userCode = logic.getNextEid();
			}catch(RuntimeException e) {
				request.setAttribute("error", e.getMessage());
		        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
		        return;
			}
		}
		
		String hashPass = logic.encryption(plainPass);
		
		User user = new User(isAdmin,userCode,name,hashPass);
		
		if(logic.registerUser(user)) {
			request.setAttribute("message", "ユーザーIDは"+userCode+"です。IDは今後表示されません。");
            request.getRequestDispatcher("/WEB-INF/jsp/registerSuccess.jsp").forward(request, response);
            return;
		}else {
			request.setAttribute("error","登録に失敗しました。もう一度お試しください。");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
			return;
		}
	}

}
