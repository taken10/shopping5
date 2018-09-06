package shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//入力された評価をDBに反映させるサーブレット

@WebServlet("/evaluationServlet")
public class evaluationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//商品一覧画面で入力された値を取得
		request.setCharacterEncoding("UTF-8");
		Enumeration<String> names = request.getParameterNames();

		String name = ""; // 現在のパラメータ名
		String itemId = ""; // 商品ID

		// 評価ボタンがクリックされた場所を特定
		// クリックされた評価ボタンの値（value）と、リストボックスの値が取得できる
		// 評価ボタンをクリックした後のURLにパラメータが記載されています
		while (names.hasMoreElements()) {
			// 渡ってきたパラメータを順番に処理
			// パラメータ名を取得
			name = names.nextElement();

			// 評価ボタンがクリックされている場合は「評価」のパラメータが取得できる
			if ("評価".equals(request.getParameter(name))) {
				// 評価ボタンに付属している値（value）が商品IDになる
				itemId = name;
			}
		}

		// ドロップダウンリストで入力された対象のnameの評価点を取得
		Integer value = Integer.parseInt(request.getParameter(itemId + "eva"));

		//DAOクラスインスタンス化
		ShoppingDao dao = new ShoppingDao();

		try {

			// 商品IDを基に評価点を更新
			dao.updateEvaluation(itemId, value);
			// 商品IDを基に評価回数をプラスする
			dao.updateEvaluationCount(itemId);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			dao.close();
		}

		// 評価結果画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("./contents/evaluation.jsp");
		rd.forward(request, response);
	}
}