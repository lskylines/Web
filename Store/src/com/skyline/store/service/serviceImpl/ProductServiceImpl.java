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
	 * ������Ʒpid��ѯ��Ʒ��Ϣ��չʾ��Ʒ����ҳ��Ϣ
	 */
	public Product findByPid(String pid) throws SQLException {
		ProductDao dao = new ProductDaoImpl();
		return dao.findByPid(pid);
		
	}

	public PageModel findProductByCidWithPage(String cid, int curNum)  throws SQLException{
		//����pageModel����
		//ͳ�Ƶ�ǰ��������Ʒ�ĸ���
		ProductDao dao = new ProductDaoImpl();
		//����cid��ѯ�ܼ�¼��
		int totalRecords = dao.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 6);
		List list = dao.findProductByCidWithPage(cid,pm.getStartIndex(), pm.getPageSize());
		//��������
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductByCidWithPage&cid=" + cid);
		return pm;
	}
	
	

}
