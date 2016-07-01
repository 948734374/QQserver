package com.qq.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
public static final String FILENAME="server.properties";
private static Properties pro=new Properties();
static{
	try {
		pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("∂¡»°“Ï≥£");
		e.printStackTrace();
	}
}

public static String readPro(String key){
	return pro.getProperty(key);
}
}
