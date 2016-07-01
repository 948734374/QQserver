package com.qq;

import java.io.Serializable;

public class Find implements Serializable{
private int type ;        //1。精确      2.所有
private String faccount;
public static final int ONE=1;

public static final int All=2;

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}

public String getFaccount() {
	return faccount;
}

public void setFaccount(String faccount) {
	this.faccount = faccount;
}


}
