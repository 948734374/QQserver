package com.qq.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectUtil {
public static void writeObject(Socket socket, Object object)throws IOException
{
	
	ObjectOutputStream oStream=new ObjectOutputStream(socket.getOutputStream());
	
	oStream.writeObject(object);
	}
public static Object readObject(Socket socket)throws IOException ,ClassNotFoundException{
	ObjectInputStream iStream=new ObjectInputStream(socket.getInputStream());
	return iStream.readObject();
}
}
