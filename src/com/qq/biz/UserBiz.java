package com.qq.biz;

import java.util.List;

import com.qq.AddRSMsg;
import com.qq.RegeditRS;
import com.qq.User;
import com.qq.dao.UserDAO;
import com.qq.dao.UserDAOImpl;

public class UserBiz {
	private UserDAO userDao;

public UserBiz(){
	userDao=new UserDAOImpl();
	}
public User isLogin(User user){
	user=userDao.isLogin(user.getAccount(), user.getPassword());
	if (user!=null) {
		user.setFriends(userDao.queryFriends(user.getAccount()));
	}
	return user;
}

public RegeditRS regeditRS(User user){
	RegeditRS regeditRS=new RegeditRS();
	int i=userDao.getNextAccount();
	String account=String.format("0000%05d%n", i);
	user.setAccount(account);  //ÉèÖÃÓÃ»§×¢²áÕËºÅ
	boolean b=userDao.addUser(user);
	if (b) {
		regeditRS.setMsString("ÄúµÄµÇÂ¼ÕËºÅÎª£º"+account);
		regeditRS.setRs(true);
		
	}else {
		
		regeditRS.setMsString("ÕËºÅ×¢²áÊ§°Ü");
		regeditRS.setRs(false);
	}
	return regeditRS;
}
public User queryByaccount(String account){
	return userDao.queryByAccount(account);
	
}
public List<User> queryAll(){
	return userDao.queryAll();
}
public void addFriends(AddRSMsg msg){
	userDao.addFriends(msg.getForm(), msg.getTo());
}

}