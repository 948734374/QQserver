package com.qq.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.qq.User;
import com.qq.biz.ServerBiz;
import com.qq.biz.UserBiz;
import com.qq.util.ObjectUtil;
import com.qq.AddRSMsg;
import com.qq.AddfriendsMsg;
import com.qq.Find;
import com.qq.RegeditRS;
import com.qq.SendMsg;

public class ClientThread extends Thread {
	private Socket socket;
	private UserBiz userBiz;
    
	
	public ClientThread(Socket socket) {
		this.socket = socket;
		
		userBiz = new UserBiz();

	}

	public void run() {
		while (true) {
			try {
				Object object = ObjectUtil.readObject(socket);

				if (object instanceof User) {

					User user = (User) object;

					if (user.getAccount() != null) {
						// 登录
						user = userBiz.isLogin(user);
						if(user!=null){
							
							//登陆成功     account + socket----->map
							ServerBiz.getMaps().put(user.getAccount(), socket);
						}
						ObjectUtil.writeObject(socket, user);// 返回结果给客户端

					} else {
						// 注册
						RegeditRS rs = userBiz.regeditRS(user);
						ObjectUtil.writeObject(socket, rs);// 返回结果给客户端
					}
				} else if (object instanceof Find) {
					Find find = (Find) object;
					List<User> list = new ArrayList<User>();
					switch (find.getType()) {
					case Find.ONE:{
						User user = userBiz.queryByaccount(find.getFaccount());
						if (user != null)
							list.add(user);
					}
						break;
					case Find .All:{
						list.addAll(userBiz.queryAll());}
						break;
					}
					ObjectUtil.writeObject(socket, list);
				}else if (object instanceof AddfriendsMsg) {
					AddfriendsMsg msg=(AddfriendsMsg)object;
					System.out.println(socket);
					System.out.println(msg.getFrom()+"要添加"+msg.getTo());
                    Socket fSocket=ServerBiz.getMaps().get(msg.getTo().getAccount());
                    if (fSocket!=null) {
						//用户在线
					ObjectUtil.writeObject(fSocket, msg);
                    }else{
						//用户不在线
                    	
                    }
					
				}else if (object instanceof AddRSMsg) {
					AddRSMsg msg =(AddRSMsg)object;
					Socket fSocket=ServerBiz.getMaps().get(msg.getTo().getAccount());
					if (msg.isAgree()) {
						//保存好友关系
	                     userBiz.addFriends(msg); 					
					} 
					if (fSocket!=null) {
							//用户在线
						ObjectUtil.writeObject(fSocket, msg);
	                    }else{
							//用户不在线
	                    	
	                    }
				}else if (object instanceof SendMsg){
					SendMsg msg=(SendMsg)object;
					 Socket fSocket=ServerBiz.getMaps().get(msg.getTo().getAccount());
	                    if (fSocket!=null) {
							//用户在线
						ObjectUtil.writeObject(fSocket, msg);
	                    }else{
							//用户不在线
	                    	
	                    }
				}
				// 登录验证
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
