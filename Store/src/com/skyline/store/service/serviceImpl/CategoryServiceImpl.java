package com.skyline.store.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.dao.CategoryDao;
import com.skyline.store.dao.daoImpl.CategoryDaoImpl;
import com.skyline.store.domain.Category;
import com.skyline.store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	public List<Category> findAll() throws SQLException {
		CategoryDao dao = new CategoryDaoImpl();
		return dao.findAll();
	}

}
