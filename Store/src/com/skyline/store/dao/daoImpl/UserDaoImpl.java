package com.skyline.store.dao.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.skyline.store.dao.UserDao;
import com.skyline.store.domain.User;
import com.skyline.store.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	public void regist(User userBean) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql  ="INSERT INTO user VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {userBean.getUid(), userBean.getUsername(), userBean.getPassword(), userBean.getName(), userBean.getEmail(),
				userBean.getTelephone(), userBean.getBirthday(), userBean.getSex(), userBean.getState(), userBean.getCode()};
		qr.update(sql, params);
	}

	public User login(User user) throws SQLException {
		String sql = "SELECT * FROM user WHERE username=? and password=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return  qr.query(sql,  new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
	}

}
