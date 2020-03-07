package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Account;

public class AccountDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	final String URL = "jdbc:mysql://us-cdbr-iron-east-04.cleardb.net/heroku_8809a444f8ab16b?reconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	final String USER = "b864de264aae32";
	final String PASS = "96560f71";
    public void insertAccount(Account account){
    	try {
	    // mySQLの接続
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(URL, USER, PASS);
    		ps = con.prepareStatement("insert into account(date,category, content, price, loginId) values (?,?,?,?,?)");
            ps.setString(1, account.getDate());
            ps.setString(2, account.getCategory());
            ps.setString(3, account.getContent());
            ps.setInt(4, account.getAmount());
            ps.setInt(5, account.getLoginId());
//            ps.setString(5, strDate);
            int ctr = ps.executeUpdate(); //更新した件数を表示させるのにctrを使える
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	} catch (ClassNotFoundException sqle) {
    		sqle.printStackTrace();
	    } finally {
	    	closeSql(con, ps, rs);
        }
    }

    public void deleteAccount(int id) {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			ps = con.prepareStatement("delete from account where id = ?");
			ps.setInt(1, id);
			int ctr = ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			closeSql(con, ps, rs);
		}

    }

    public List<Account> showAccount(int loginId, String year, String month) {
    	List<Account> accountList = new ArrayList();
    	try {
	    // mySQLの接続
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(URL, USER, PASS);
    		ps = con.prepareStatement("select * from account where loginId = ? order by date asc");
    		ps.setInt(1, loginId);
            rs = ps.executeQuery();
            while(rs.next()) {
            	Account account = new Account();
            	account.setId(rs.getInt("id"));
            	account.setDate(rs.getString("date"));
            	account.setCategory(rs.getString("category"));
            	account.setContent(rs.getString("content"));
            	account.setAmount(rs.getInt("price"));
//            	System.out.println(account.getDate().substring(0, 4));
//            	System.out.println(account.getDate().substring(5, 7));
//            	System.out.println(year);
//            	System.out.println(month);
            	if(account.getDate().substring(0, 4).equals(year) && account.getDate().substring(5, 7).equals(month)) {
            		accountList.add(account);
            	}
            }
    	} catch (SQLException sqle) {
    		sqle.printStackTrace();
    	} catch (ClassNotFoundException sqle) {
    		sqle.printStackTrace();
	    } finally {
	    	closeSql(con, ps, rs);
        }
		return accountList;
    }

    public void modifyAccount(int id,String date, String category, String content, int amount){
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(URL, USER, PASS);
		ps = con.prepareStatement("update account set date = ?, category = ?,content = ?, price = ? where id = ?");

    	ps.setString(1,date);
    	ps.setString(2,category);
    	ps.setString(3,content);
    	ps.setInt(4,amount);
    	ps.setInt(5,id);

    	int ctr = ps.executeUpdate();

    	} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			closeSql(con, ps, rs);
    	}

    }

    static void closeSql(Connection con, PreparedStatement ps, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            System.err.println("Error closeSql Func");
            sqle.printStackTrace();
        }
    }
}