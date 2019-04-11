package com.skyline.store.service.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.dao.ProductDao;
import com.skyline.store.dao.daoImpl.ProductDaoImpl;
import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.Product;
import com.skyline.store.service.ProductService;

public class ProductServiceImpl implements ProductService {
	ProductDao dao = new ProductDaoImpl();
	public List<Product> findHots() throws SQLException {
		return dao.findHots();
	}

	public List<Product> findNews() throws SQLException {
		return dao.findNews();
	}

	/**
	 * 根据商品pid查询商品信息，展示商品详情页信息
	 */
	public Product findByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDaoImpl();
		return dao.findByPid(pid);
		
	}

	public PageModel findProductByCidWithPage(String cid, int curNum)  throws SQLException{
		//创建pageModel对象
		//统计当前分类下商品的个数
		ProductDao dao = new ProductDaoImpl();
		//根据cid查询总记录数
		int totalRecords = dao.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 6);
		List list = dao.findProductByCidWithPage(cid,pm.getStartIndex(), pm.getPageSize());
		//关联集合
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductByCidWithPage&cid=" + cid);
		return pm;
	}
	
	

}
