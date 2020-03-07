package service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import entity.Login;

/**
 * Servlet implementation class LoginAccount
 */
@WebServlet("/LoginNewAccount")
public class LoginNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginNewAccount() {
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
		String page = "./register.jsp";
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		Login login = new Login(userId, password);
		LoginDao ld = new LoginDao();
		Login la = ld.creatId(login);
		HttpSession session = request.getSession();
		if(la != null) {
			session.setAttribute("loginStatus", "true");
			session.setAttribute("loginId", la.getLoginId());
		}else {
			page = "./newAccount.jsp";
			session.setAttribute("loginStatus", "false");
			request.setAttribute("errorLogin", "すでに登録されたユーザーIDです。");
		}

		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

	}

}
