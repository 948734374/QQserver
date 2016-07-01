package com.qq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
private static String driverClass;  //驱动类
private static String user;         //驱动类
private static String password;     //驱动类
private static String url;          //驱动类



static{
	driverClass=PropertiesUtil.readPro("driverClass");
	user=PropertiesUtil.readPro("user");
	password =PropertiesUtil.readPro("password");
	url=PropertiesUtil.readPro("url");

	try {
		Class.forName(driverClass);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("数据库启动加载失败！"+driverClass);
		e.printStackTrace();
	}

}
public static Connection getConn() throws SQLException{
	return DriverManager.getConnection(url,user,password);
}
public static void main(String[] args) {
	try {
		System.out.println(DBUtil.getConn());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

