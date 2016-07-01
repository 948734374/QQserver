
package com.qq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qq.User;
import com.qq.util.DBUtil;

public class UserDAOImpl implements UserDAO {

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	String sql;
	List<User> list;

	private void closeAll() {
		try {

			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User isLogin(String account, String password) {
		sql = "select * from qq_user where account=? and password =? ";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setAge(rs.getShort("age"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setImg(rs.getString("img"));
				user.setNickname(rs.getString("nickname"));
				System.out.println("登陆成功！");
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	@Override
	public boolean addUser(User user) {
		sql = "insert into qq_user(password,nickname,age,email,img) values(?,?,?,?,?)";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getNickname());
			pst.setInt(3, user.getAge());
			pst.setString(4, user.getEmail());
			pst.setString(5, user.getImg());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeAll();
		}
		return false;
	}

	public List<User> queryFriends(String account) {
		List<User> list = new ArrayList<User>();
		sql = "select * from qq_user where account in (select qq_fid from qq_friend where qq_uid=?) ";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			rs = pst.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getShort("age"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				list.add(u);
				System.out.println(u.getNickname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;
	}

	@Override
	public int getNextAccount() {
		// TODO Auto-generated method stub
		sql = "select * from qq_account";
		int id = -1;
		try {
			con = DBUtil.getConn();
			con.setAutoCommit(false); // 设置不自动提交
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id") + 1;
				sql = "update qq_account set id=?";
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);

			}

			con.commit(); // 手动提交
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true); // 设置自动提交
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeAll();
		}

		return id;
	}

	@Override
	public User queryByAccount(String account) {
		// TODO Auto-generated method stub
		sql = "select * from qq_user " + "where account =?";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			rs = pst.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getShort("age"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<User>();
		sql = "select * from qq_user";
		try {
			con = DBUtil.getConn();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setAccount(rs.getString("account"));
				u.setAge(rs.getShort("age"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				u.setImg(rs.getString("img"));
				u.setNickname(rs.getString("nickname"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return list;

	}

	@Override
	public void addFriends(User u, User f) {
		// TODO Auto-generated method stub
		sql = "insert into qq_qriends values (?,?)";
		try {
			con = DBUtil.getConn();
			con.setAutoCommit(false); // 设置不自动提交
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getAccount());
			pst.setString(2, f.getAccount());
			pst.executeUpdate();
			pst.clearParameters();
			pst.setString(1, f.getAccount());
			pst.setString(2, u.getAccount());
			pst.executeUpdate();
			con.commit(); // 手动提交
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true); // 设置自动提交
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeAll();
		}
	}
}
