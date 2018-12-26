package com.dreamcc.product.server.service.impl;


import com.dreamcc.mall.common.common.ResponseCode;
import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.common.entity.Category;
import com.dreamcc.mall.common.util.DateTimeUtil;
import com.dreamcc.mall.user.client.CategoryClient;
import com.dreamcc.mall.product.common.ProductDetailVo;
import com.dreamcc.mall.product.common.ProductListVo;
import com.dreamcc.product.server.dao.ProductDao;
import com.dreamcc.product.server.entity.Product;
import com.dreamcc.product.server.service.IProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 10:40
 * @Version: V1.0
 */
@Service("productService")
public class ProductServiceImpl implements IProductService {

	private ProductDao productDao;
	private CategoryClient categoryClient;

	@Autowired
	public ProductServiceImpl(ProductDao productDao, CategoryClient categoryClient) {
		this.productDao = productDao;
		this.categoryClient = categoryClient;
	}

	@Override
	public ServerResponse addProduct(Product product) {
		if (product != null) {
			int result = productDao.addProduct(product);
			if (result > 0) {
				return ServerResponse.createBySuccessMessage("add product success！");
			}
		}
		return ServerResponse.createByErrorMessage("add product failed！");
	}

	@Override
	public ServerResponse updateProduct(Product product) {
		if (product != null) {
			int result = productDao.updateProduct(product);
			if (result > 0) {
				return ServerResponse.createBySuccessMessage("update product success！");
			}
		}
		return ServerResponse.createByErrorMessage("update product failed！");
	}

	@Override
	public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
		if (productId == null || status == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		Product product = new Product();
		product.setId(productId);
		product.setStatus(status);
		int rowCount = productDao.updateProduct(product);
		if (rowCount > 0) {
			return ServerResponse.createBySuccess("update product sell state success");
		}
		return ServerResponse.createByErrorMessage("update product sell state  failed");
	}

	@Override
	public ServerResponse getProductDetailsById(Integer productId) {
		if (productId == null) {
			return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
		}
		Product product = productDao.getProductById(productId);
		if (product == null) {
			return ServerResponse.createByErrorMessage("product sold out or product is deleted");
		}
		ProductDetailVo productDetailVo = assembleProductDetailVo(product);
		return ServerResponse.createBySuccess(productDetailVo);
	}

	@Override
	public ServerResponse<PageInfo> getProductList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Product> productsList = productDao.getProductList();
		List<ProductListVo> productListVoList = Lists.newArrayList();
		for (Product productItem : productsList) {
			ProductListVo productListVo = assembleProductListVo(productItem);
			productListVoList.add(productListVo);
		}
		PageInfo pageResult = new PageInfo(productsList);
		pageResult.setList(productListVoList);
		return ServerResponse.createBySuccess(pageResult);
	}

	@Override
	public ServerResponse<PageInfo> searchProduct(String productName, int productId, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if (StringUtils.isNotBlank(productName)) {
			productName = new StringBuilder().append("%").append(productName).append("%").toString();
		}
		List<Product> productList = productDao.searchProduct(productName, productId);
		List<ProductListVo> productListVoList = Lists.newArrayList();
		for (Product productItem : productList) {
			ProductListVo productListVo = assembleProductListVo(productItem);
			productListVoList.add(productListVo);
		}
		PageInfo pageResult = new PageInfo(productList);
		pageResult.setList(productListVoList);
		return ServerResponse.createBySuccess(pageResult);
	}

	private ProductListVo assembleProductListVo(Product product) {
		ProductListVo productListVo = new ProductListVo();
		productListVo.setId(product.getId());
		productListVo.setName(product.getName());
		productListVo.setCategoryId(product.getCategoryId());
		productListVo.setSubtitle(product.getSubtitle());
		productListVo.setStatus(product.getStatus());
		return productListVo;
	}

	private ProductDetailVo assembleProductDetailVo(Product product) {
		ProductDetailVo productDetailVo = new ProductDetailVo();
		productDetailVo.setId(product.getId());
		productDetailVo.setSubtitle(product.getSubtitle());
		productDetailVo.setPrice(product.getPrice());
		//productDetailVo.setMainImage(product.getMainImage());
		productDetailVo.setSubImages(product.getSubImages());
		productDetailVo.setCategoryId(product.getCategoryId());
		productDetailVo.setDetail(product.getDetail());
		productDetailVo.setName(product.getName());
		productDetailVo.setStatus(product.getStatus());
		productDetailVo.setStock(product.getStock());
		Category category = categoryClient.selectByPrimaryKey(product.getCategoryId());
		if (category == null) {
			productDetailVo.setParentCategoryId(0);
		} else {
			productDetailVo.setParentCategoryId(category.getParentId());
		}
		productDetailVo.setCreateTime(DateTimeUtil.dateToStr(product.getCreateTime()));
		productDetailVo.setUpdateTime(DateTimeUtil.dateToStr(product.getUpdateTime()));
		return productDetailVo;
	}
}

