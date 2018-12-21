package com.dreamcc.product.server.service;


import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.product.server.entity.Product;
import com.github.pagehelper.PageInfo;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 10:39
 * @Version: V1.0
 */
public interface IProductService {

	ServerResponse addProduct(Product product);

	ServerResponse updateProduct(Product product);

	ServerResponse<String> setSaleStatus(Integer productId, Integer status);

	ServerResponse getProductDetailsById(Integer productId);

	ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

	ServerResponse<PageInfo> searchProduct(String productName, int productId, int pageNum, int pageSize);
}
