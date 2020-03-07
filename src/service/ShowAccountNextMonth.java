package service;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Account;
import entity.AccountList;
import entity.YearMonth;
/**
 * Servlet implementation class InsertAccount
 */
@WebServlet("/ShowAccountNextMonth")
public class ShowAccountNextMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAccountNextMonth() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		int loginId = (int) session.getAttribute("loginId");
		YearMonth ym = (YearMonth) session.getAttribute("ym");
		int monthTemp = Integer.parseInt(ym.getMonth()) + 1;
		int yearTemp = Integer.parseInt(ym.getYear());
		if(monthTemp > 12) {
			monthTemp = 1;
			yearTemp = Integer.parseInt(ym.getYear()) + 1;
		}
		String year = String.format("%04d", yearTemp);
		String month = String.format("%02d", monthTemp);
		ym.setYear(year);
		ym.setMonth(month);
		AccountDao accountDao = new AccountDao();
		List<Account> accountList= accountDao.showAccount(loginId, year, month);
		AccountList al = new AccountList();
		for(int i = 0; i < accountList.size(); i++) {
			if(accountList.get(i).getCategory().equals("食費")) {
				al.setFood(al.getFood() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("日用品")) {
				al.setDailyItem(al.getDailyItem() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("交際費")) {
				al.setFriend(al.getFriend() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("交通費")) {
				al.setTransport(al.getTransport() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("衣服費")) {
				al.setCloth(al.getCloth() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("医療費")) {
				al.setHospital(al.getHospital() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("趣味費")) {
				al.setHobby(al.getHobby() + accountList.get(i).getAmount());
			}else if(accountList.get(i).getCategory().equals("その他")) {
				al.setOther(al.getOther() + accountList.get(i).getAmount());
			}
			al.setTotal(al.getTotal() + accountList.get(i).getAmount());
		}
		session.setAttribute("accountList", accountList);
		session.setAttribute("al", al);
		session.setAttribute("loginId", loginId);
		RequestDispatcher rd = request.getRequestDispatcher("./show.jsp");
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}