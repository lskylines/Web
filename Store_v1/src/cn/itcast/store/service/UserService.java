package cn.itcast.store.service;

import java.sql.SQLException;

import cn.itcast.store.domain.User;

public interface UserService {
	void userRegister(User user) throws  SQLException;
	User userLogin(User user) throws SQLException;
}
