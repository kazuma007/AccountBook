package service;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Account;
/**
 * Servlet implementation class InsertAccount
 */
@WebServlet("/InsertAccount")
public class InsertAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String date = request.getParameter("date");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
//		String amount = request.getParameter("priceAmount");
		int amount = Integer.parseInt(request.getParameter("priceAmount"));
		HttpSession session = request.getSession();
		int loginId = (int) session.getAttribute("loginId");
		session.setAttribute("loginId", loginId);
		Account account = new Account(date, category, content, amount, loginId);
		AccountDao accountDao = new AccountDao();
		accountDao.insertAccount(account);
		RequestDispatcher rd = request.getRequestDispatcher("./register.jsp");
		rd.forward(request, response);
	}
}