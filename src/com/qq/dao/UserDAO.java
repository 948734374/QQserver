package com.qq.dao;

import java.util.List;

import com.qq.User;

public interface UserDAO {
	/*
	 * 验证用户
	 */
	public User isLogin(String account, String password);

	/*
	 * 注册用户
	 *
	 * 
	 */
	public boolean addUser(User u);
	

	public int getNextAccount();

	public User queryByAccount(String account);

	/*
	 * 获取好友
	 */
	
	public List<User> queryFriends(String account);
	/*
	 * 根据账号查找用户
	 */

	public List<User> queryAll();
	/*
	 * 查找所有用户
	 */
	public void addFriends(User u,User f);
}
