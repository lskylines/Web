package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.dao.Impl.CategoryDaoImpl;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	public List<Category> getAllCats() throws Exception {
		CategoryDao categoryDao = new CategoryDaoImpl();
		return categoryDao.getAllCats();
	}
		
}
