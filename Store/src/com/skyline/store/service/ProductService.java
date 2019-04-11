package com.skyline.store.service;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.Product;

public interface ProductService {
	/**
	 * 查询热门商品
	 */
	List<Product> findHots() throws SQLException;
	
	/**
	 * 查询最新商品
	 * @return
	 * @throws SQLException
	 */
	List<Product>findNews() throws SQLException; 
	
	/**
	 * 根据商品pid查询商品相关信息，用于商品详情页
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	Product findByPid(String pid) throws SQLException;

	/**
	 * 根据cid和curNum查询数据
	 * @param cid
	 * @param curNum
	 * @return
	 */
	PageModel findProductByCidWithPage(String cid, int curNum) throws SQLException;
	
}
