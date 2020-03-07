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
@WebServlet("/ModifyAccount")
public class ModifyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAccount() {
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
		String pId = request.getParameter("pageId");
		int pageId = Integer.parseInt(pId);

		String date = request.getParameter("date");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		String pAmount = request.getParameter("priceAmount");
		int price = Integer.parseInt(pAmount);

//		List<Account> accountList = (List<Account>) session.getAttribute("accountList");
//		Account account = accountList.get(pageId);
//		int aId = account.getId();

		AccountDao ad = new AccountDao();
		ad.modifyAccount(pageId, date, category, content, price);


		Account account = new Account(pageId, date, category, content, price);

		HttpSession session = request.getSession();
		session.setAttribute("account", account);

		RequestDispatcher rd = request.getRequestDispatcher("./detail.jsp");
		rd.forward(request, response);

	}
}