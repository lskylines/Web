package cn.itcast.store.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	public List<Product> findNews() throws Exception {
		String sql = "SELECT  * FROM product WHERE pflag=0 ORDER BY pdate DESC LIMIT 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
		
	}

	public List<Product> findHots() throws Exception {
		String sql = "SELECT * FROM product WHERE pflag=0 AND is_hot=1 ORDER BY pdate DESC LIMIT 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public Product  findProductByPid(String pid) throws Exception {
		String sql = "SELECT * FROM product WHERE pid=?";
		System.out.println(sql);
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class), pid);
	
	}

	public List<Product> findProductByCid(String cid) throws Exception {
		String sql = "SELECT * FROM product WHERE cid=? LIMIT 0,6";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid);
	
	}
		
}
