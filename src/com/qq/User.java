package com.qq;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
private String account;
private String password;
private String nickname;
private int age;
private String email;
private String img;

private List<User> friends; //                    此用户所有好友

public List<User> getFriends() {
	return friends;
}

public void setFriends(List<User> friends) {
	this.friends = friends;
}

public String getEmail() {
	// TODO Auto-generated method stub
	return null;
}

public User(){
	super();
}

public String getAccount() {
	return account;
}

public String getPassword() {
	return password;
}

public String getNickname() {
	return nickname;
}

public int getAge() {
	return age;
}

public String getImg() {
	return img;
}

public User(String account, String password, String nickname, int age, String email, String img) {
	super();
	this.account = account;
	this.password = password;
	this.nickname = nickname;
	this.age = age;
	this.email = email;
	this.img = img;
}

public void setAccount(String account) {
	this.account = account;
}
public void setPassword(String password) {
	this.password = password;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public void setAge(int i) {
	this.age = i;
}
public void setEmail(String email) {
	this.email = email;
}
public void setImg(String img) {
	this.img = img;
}





}
