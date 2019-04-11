package com.skyline.store.dao;

import java.sql.SQLException;
import java.util.List;

import com.skyline.store.domain.Product;

public interface ProductDao {
	/**
	 * ��ѯ������Ʒ
	 * @return
	 * @throws SQLException
	 */
	List<Product> findHots() throws SQLException;
	
	/**
	 * ��ѯ������Ʒ
	 */
	List<Product> findNews() throws SQLException;
	
	/**
	 * ������Ʒpid��ѯ��Ʒ��Ϣ�������Ʒ����ҳ
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	Product findByPid(String pid) throws SQLException; 
	
	/**
	 * ����cid��ѯ�ܼ�¼��
	 * @param cid
	 * @return
	 * @throws SQLException
	 */
	int findTotalRecords(String cid) throws SQLException;
	
	
	/**
	 * ���ݲ���cid,startIndex,pageSize��ѯ����Ԫ��
	 * @param cid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	List findProductByCidWithPage(String cid, int startIndex, int pageSize) throws SQLException;
}
