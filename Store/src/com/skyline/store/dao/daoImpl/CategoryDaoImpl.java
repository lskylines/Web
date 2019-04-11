package com.skyline.store.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.skyline.store.dao.CategoryDao;
import com.skyline.store.domain.Category;
import com.skyline.store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	public List<Category> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM category";
		List<Category> list = qr.query(sql,  new BeanListHandler<Category>(Category.class));
		return list;
	}

}
