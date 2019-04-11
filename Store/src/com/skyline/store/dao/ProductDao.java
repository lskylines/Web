package com.skyline.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.Product;

public interface ProductDao {
	/**
	 * 查询最热商品
	 * @return
	 * @throws SQLException
	 */
	List<Product> findHots() throws SQLException;
	
	/**
	 * 查询最新商品
	 */
	List<Product> findNews() throws SQLException;
	
	/**
	 * 根据商品pid查询商品信息，完成商品详情页
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	Product findByPid(String pid) throws SQLException; 
	
	/**
	 * 根据cid查询总记录数
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	int findTotalRecords(String cid) throws SQLException;
	
	
	/**
	 * 根据参数cid,startIndex,pageSize查询集合元素
	 * @param cid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;
}
