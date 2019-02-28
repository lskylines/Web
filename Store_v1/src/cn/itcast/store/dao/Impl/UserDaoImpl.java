package cn.itcast.store.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImpl  implements UserDao{
	public void userRegist(User user) throws SQLException {
		String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?,?, ?, ?, ?, ?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {user.getUid(), user.getUsername(), user.getPassword(),user.getName(),user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
		qr.update(sql, params);
		
		
	}
	
	public User userLogin(User user) throws SQLException{
		String sql = "SELECT * FROM user WHERE username=? and password=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
		
	}
}
