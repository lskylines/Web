package cn.itcast.store.service.serviceImpl;

import java.sql.SQLException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.Impl.UserDaoImpl;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;

public class UserServiceImpl  implements UserService{
	public void userRegister(User user) throws SQLException {
		//实现注册功能
		UserDao userDao = new UserDaoImpl();
		userDao.userRegist(user);
	}
	
	public User userLogin(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		User uu = userDao.userLogin(user);
		if(uu==null) {
			throw new RuntimeException("密码有误");
		}else {
			return uu;
		}
	}

}
