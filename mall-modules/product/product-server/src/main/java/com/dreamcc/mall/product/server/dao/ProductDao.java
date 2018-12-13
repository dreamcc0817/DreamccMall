package com.dreamcc.mall.product.server.dao;

import com.dreamcc.mall.product.server.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.dao
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 10:34
 * @Version: V1.0
 */
@Mapper
public interface ProductDao {
	/**
	 * add product
	 *
	 * @param product
	 * @return
	 */
	int addProduct(Product product);

	/**
	 * update product
	 *
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);

	/**
	 * get product
	 *
	 * @param productId
	 * @return
	 */
	Product getProductById(int productId);

	/**
	 * get product list
	 *
	 * @return
	 */
	List<Product> getProductList();

	/**
	 * search product
	 *
	 * @param productName
	 * @param productId
	 * @return
	 */
	List<Product> searchProduct(String productName, int productId);
}
