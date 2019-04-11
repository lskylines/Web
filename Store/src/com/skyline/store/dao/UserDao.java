package com.skyline.store.dao;

import java.sql.SQLException;

import com.skyline.store.domain.User;

public interface UserDao {

	void regist(User userBean) throws SQLException;

	User login(User user) throws SQLException;

}
