package com.skyline.store.service.serviceImpl;

import java.sql.SQLException;

import com.skyline.store.dao.UserDao;
import com.skyline.store.dao.daoImpl.UserDaoImpl;
import com.skyline.store.domain.User;
import com.skyline.store.service.UserService;

public class UserServiceImpl implements UserService {

	public void userRegist(User userBean) throws SQLException {
		UserDao dao = new UserDaoImpl();
		dao.regist(userBean);
		
	}

	public User login(User user) throws SQLException {
		UserDao dao = new UserDaoImpl();
		User user02 = dao.login(user);
		if(user02==null) {
			//用户账号或者密码错误
			throw new RuntimeException("账号密码有误");
		}else if(user02.getState()==0) {
			//用户未激活
			throw new RuntimeException("用户未激活");
		}else {
			return user02;
		}
	
	}

}
