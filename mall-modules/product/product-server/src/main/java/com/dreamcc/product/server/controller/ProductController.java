package com.dreamcc.product.server.controller;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.product.common.ProductDetailVo;
import com.dreamcc.product.server.entity.Product;
import com.dreamcc.product.server.service.IProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/11/24 10:41
 * @Version: V1.0
 */
@Api(value = "API - ProductController", description = "Product Interface Module")
@RestController
@RequestMapping("/product")
public class ProductController {

	private IProductService iProductService;

	@Autowired
	public ProductController(IProductService iProductService) {
		this.iProductService = iProductService;
	}

	@ApiOperation(value = "add product", notes = "the module is add product")
	@PostMapping("/addProduct")
	public ServerResponse addProduct(@ModelAttribute Product product) {
		return iProductService.addProduct(product);
	}

	@ApiOperation(value = "update product", notes = "the module is update product")
	@PutMapping(value = {"/updateProduct"})
	public ServerResponse updateProduct(@ModelAttribute Product product) {
		return iProductService.updateProduct(product);
	}

	@ApiOperation(value = "update product", notes = "the module is update product")
	@PutMapping(value = {"/updateProductState"})
	public ServerResponse updateProductState(Integer productId, Integer status) {
		return iProductService.setSaleStatus(productId, status);
	}

	@ApiOperation(value = "get product details", notes = "the module is get product details")
	@GetMapping("/getProductDetails/{productId}")
	public ServerResponse<ProductDetailVo> getProductDetailsById(@PathVariable Integer productId) {
		return iProductService.getProductDetailsById(productId);
	}

	@ApiOperation(value = "get product list", notes = "the module is get product list")
	@GetMapping("/getProductList")
	public ServerResponse getList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		return iProductService.getProductList(pageNum, pageSize);
	}

	@ApiOperation(value = "search product", notes = "the module is search product")
	@GetMapping("/searchProduct")
	public ServerResponse<PageInfo> searchProduct(String productName, int productId,
	                                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
	                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

		return iProductService.searchProduct(productName, productId, pageNum, pageSize);
	}
}
