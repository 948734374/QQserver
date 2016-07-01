package com.qq.biz;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.qq.Find;
import com.qq.thread.ClientThread;
import com.qq.util.PropertiesUtil;

public class ServerBiz {
	int port;
	ServerSocket ss;
	private static Map<String, Socket> maps;

	public static Map<String, Socket> getMaps() {
		return maps;
	}

	/*
	 * 启动服务
	 */
	public void startService() throws IOException {
		maps = new HashMap<String, Socket>();
		String sport = PropertiesUtil.readPro("prot");
		if (sport != null) {
			port = Integer.parseInt(sport); // 如果端口号已给出，则指定端口号，否则默认端口
		} else
			port = (6000);
		ss = new ServerSocket(port);
		while (true) {
			Socket socket = ss.accept();
			System.out.println("客户端链接成功");
			// 启动线程，独立处理客户事情

			new ClientThread(socket).start();

		}

	}

	public void stopServer() throws IOException {

		ss.close();

	}

}
