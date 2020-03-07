package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.AccountList;
import entity.Email;
import entity.YearMonth;


/**
 * Servlet implementation class SendAnEmail
 */
@WebServlet("/SendAnEmail")
public class SendAnEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendAnEmail() {
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
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		HttpSession httpSession = request.getSession();
		YearMonth ym = (YearMonth) httpSession.getAttribute("ym");
		AccountList al = (AccountList) httpSession.getAttribute("al");
		new Email().send(ym, al, email);
		RequestDispatcher rd = request.getRequestDispatcher("./show.jsp");
		rd.forward(request, response);
	}
}
