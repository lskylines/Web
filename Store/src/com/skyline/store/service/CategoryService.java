package com.skyline.store.service;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.Category;

public interface CategoryService {
	/**
	 * ��ѯ������Ϣ
	 * @return
	 * @throws SQLException
	 */
	List<Category> findAll() throws SQLException;
}
