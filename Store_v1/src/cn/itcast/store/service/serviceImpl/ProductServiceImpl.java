package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.Impl.ProductDaoImpl;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;

public class ProductServiceImpl implements ProductService {
	ProductDao productDao = new ProductDaoImpl();
	public List<Product> findNews() throws Exception {
		
		return productDao.findNews();
	}

	public List<Product> findHots() throws Exception {
		return productDao.findHots();
	}

	public Product findProductByPid(String pid) throws Exception {
		return productDao.findProductByPid(pid);
	}

	public List<Product> findProductByCid(String cid) throws Exception {
		ProductDao productDao = new ProductDaoImpl();
		return productDao.findProductByCid(cid);
		
	}
	
	
}
