package com.skyline.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.Category;

public interface CategoryDao {
	/**
	 * ��ѯ������Ϣ
	 * @return
	 * @throws SQLException
	 */
	List<Category> findAll() throws SQLException;
}
