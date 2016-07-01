package com.qq.dao;

import java.util.List;

import com.qq.User;

public interface UserDAO {
	/*
	 * ��֤�û�
	 */
	public User isLogin(String account, String password);

	/*
	 * ע���û�
	 *
	 * 
	 */
	public boolean addUser(User u);
	

	public int getNextAccount();

	public User queryByAccount(String account);

	/*
	 * ��ȡ����
	 */
	
	public List<User> queryFriends(String account);
	/*
	 * �����˺Ų����û�
	 */

	public List<User> queryAll();
	/*
	 * ���������û�
	 */
	public void addFriends(User u,User f);
}
