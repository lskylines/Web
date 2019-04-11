package com.skyline.store.service;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.PageModel;
import com.skyline.store.domain.Product;

public interface ProductService {
	/**
	 * ��ѯ������Ʒ
	 */
	List<Product> findHots() throws SQLException;
	
	/**
	 * ��ѯ������Ʒ
	 * @return
	 * @throws SQLException
	 */
	List<Product>findNews() throws SQLException; 
	
	/**
	 * ������Ʒpid��ѯ��Ʒ�����Ϣ��������Ʒ����ҳ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	Product findByPid(String pid) throws SQLException;

	/**
	 * ����cid��curNum��ѯ����
	 * @param cid
	 * @param curNum
	 * @return
	 */
	PageModel findProductByCidWithPage(String cid, int curNum) throws SQLException;
	
}
