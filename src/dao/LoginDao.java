package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Login;

public class LoginDao {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	final String URL = "";
	final String USER = "";
	final String PASS = "";
	public Login searchId(Login login) {
		Login lg = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			ps = con.prepareStatement("select * from login where userId = ?");
			ps.setString(1, login.getUserId());
			rs = ps.executeQuery();
			while(rs.next()) {
				lg = new Login();
				lg.setUserId(rs.getString("userId"));
				lg.setPassword(rs.getString("password"));
				lg.setLoginId(rs.getInt("loginId"));
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
	    	closeSql(con, ps, rs);
        }
		return lg;
	}
	public Login creatId(Login login) {
		Login lg = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			ps = con.prepareStatement("select * from login where userId = ?");
			ps.setString(1, login.getUserId());
			rs = ps.executeQuery();
			if (rs.next()) {
			} else {
				lg = insertId(login);
			}

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
	    	closeSql(con, ps, rs);
        }
		return lg;
	}
	public Login insertId(Login login) {
		Login lg = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASS);
			ps = con.prepareStatement("insert into login(userId, password) values (?,?)");
			ps.setString(1, login.getUserId());
			ps.setString(2, login.getPassword());
			int ctr = ps.executeUpdate();
			lg = searchId(login);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
	    	closeSql(con, ps, rs);
        }
		return lg;
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