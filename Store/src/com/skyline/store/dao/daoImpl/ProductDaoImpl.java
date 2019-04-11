package com.skyline.store.dao.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.skyline.store.dao.ProductDao;
import com.skyline.store.domain.Product;
import com.skyline.store.utils.JDBCUtils;

public class ProductDaoImpl  implements ProductDao{

	/**
	 * 查询最热商品
	 */
	public List<Product> findHots() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE is_hot=1 AND pflag=0 ORDER BY pdate DESC LIMIT 0,9";
		return qr.query(sql,  new BeanListHandler<Product>(Product.class));
	}
	
	
	/**
	 * 查询最新商品
	 */
	public List<Product> findNews() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pflag=0 ORDER BY pdate DESC  LIMIT 0, 9";
		return qr.query(sql,  new BeanListHandler<Product>(Product.class));
	}

	//根据商品pid查询商品信息，完成商品详情页
	public Product findByPid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM product WHERE pid = ?";
		return qr.query(sql,  new BeanHandler<Product>(Product.class), pid);
		
	}

	/**
	 * 查询cid分类下的总记录数
	 */
	public int findTotalRecords(String cid) throws SQLException {
		String sql  = "SELECT COUNT(*) FROM product WHERE cid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long result = (Long)qr.query(sql,  new ScalarHandler(), cid);
		return result.intValue();
	}


	//根据参数cid,startIndex,pageSize查询集合元素
	public List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException {
		String sql = "SELECT * FROM product WHERE cid=? LIMIT ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
		
	}

}
