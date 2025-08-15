package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import logic.RecordLogic;
import model.Record;

/**
 * 出勤・退勤の履歴一覧を取得して表示するサーブレット。
 */
@WebServlet("/RecordListServlet")
public class RecordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 出勤・退勤履歴を取得し、転送。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("WorkHistory".equals(action)) {
			try {
				RecordLogic logic = new RecordLogic();
				List<Record> recordList = logic.getRecordList();

				request.setAttribute("recordList", recordList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/recordList.jsp");
				dispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
				// 例外時はエラーページに飛ばすなど処理を追加可能
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "データ取得エラー");
			}
		}
	}

}
