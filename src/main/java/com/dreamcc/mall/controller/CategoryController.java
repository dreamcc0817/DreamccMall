package com.dreamcc.mall.controller;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.Category;
import com.dreamcc.mall.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-26 11:42
 * @Version: V1.0
 */
@Api(value = "API - CategoryController", description = "Category Interface Module")
@RestController
@RequestMapping("/category")
public class CategoryController {

	private ICategoryService categoryService;

	@Autowired
	public CategoryController(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@ApiOperation(value = "/getChildrenParallelCategory", notes = "the moudle is get category", response = String.class)
	@GetMapping("/getChildrenParallelCategory/{parentId}")
	public ServerResponse<List<Category>> getChildrenParallelCategory(@PathVariable int parentId) {
		return categoryService.getChildrenParallelCategory(parentId);
	}
}
