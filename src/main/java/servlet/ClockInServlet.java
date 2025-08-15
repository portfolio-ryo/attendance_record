package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logic.RecordLogic;

/**
 * 出勤打刻処理を担当するサーブレット。
 */
@WebServlet("/ClockInServlet")
public class ClockInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * 出勤打刻処理（POSTリクエスト）。
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String usercode = (String) session.getAttribute("usercode");

		if (usercode == null) {
			String message = URLEncoder.encode("ログインしてください", "UTF-8");
			response.sendRedirect("top.jsp?message=" + message);
			return;
		}

		LocalDateTime now = LocalDateTime.now();
		RecordLogic service = new RecordLogic();
		String message = service.clockIn(usercode, now);
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employee.jsp");
		dispatcher.forward(request, response);

	}

}
