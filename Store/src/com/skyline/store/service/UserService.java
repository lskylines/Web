package com.skyline.store.service;

import java.sql.SQLException;

import com.skyline.store.domain.User;

public interface UserService {

	void userRegist(User userBean) throws SQLException;

	User login(User user) throws SQLException;

}
