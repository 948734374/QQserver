package com.qq;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegeditRS implements Serializable{
	private boolean rs; //注册成功失败
	private String msString;//注册结果消息
	public boolean isRs() {
		return rs;
	}
	public void setRs(boolean rs) {
		this.rs = rs;
	}
	public String getMsString() {
		return msString;
	}
	public void setMsString(String msString) {
		this.msString = msString;
	}

}
