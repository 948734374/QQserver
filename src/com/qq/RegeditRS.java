package com.qq;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegeditRS implements Serializable{
	private boolean rs; //ע��ɹ�ʧ��
	private String msString;//ע������Ϣ
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
