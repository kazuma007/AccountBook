package service;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@WebServlet("/ShowAccount")
public class ShowAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAccount() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String year = sdfYear.format(calendar.getTime());
		SimpleDateFormat sdfMonth= new SimpleDateFormat("MM");
		String month = sdfMonth.format(calendar.getTime());
		YearMonth ym = new YearMonth(year, month);
		HttpSession session = request.getSession();
		int loginId = (int) session.getAttribute("loginId");
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

//		System.out.println(accountList);
		session.setAttribute("ym", ym);
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