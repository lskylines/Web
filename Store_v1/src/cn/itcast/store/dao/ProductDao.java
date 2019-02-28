package cn.itcast.store.dao;

import java.util.List;

import cn.itcast.store.domain.Product;

public interface ProductDao {

	List<Product> findNews() throws Exception;

	List<Product> findHots() throws Exception;

	Product  findProductByPid(String pid) throws Exception;

	List<Product>  findProductByCid(String cid) throws Exception;

}
