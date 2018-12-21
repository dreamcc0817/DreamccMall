package com.dreamcc.mall.user.server.controller;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.common.entity.Category;
import com.dreamcc.mall.user.server.service.ICategoryService;
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

	@ApiOperation(value = "get children parallel category", notes = "the module is get category")
	@GetMapping("/getChildrenParallelCategory/{parentId}")
	public ServerResponse<List<Category>> getChildrenParallelCategory(@PathVariable int parentId) {
		return categoryService.getChildrenParallelCategory(parentId);
	}

	@ApiOperation(value = "add category", notes = "the module is add category")
	@PostMapping("/addCategory")
	public ServerResponse<String> addCategory(@ModelAttribute Category category) {
		return categoryService.addCategory(category);
	}

	@ApiOperation(value = "update category name", notes = "the module is update category")
	@PutMapping("/updateCategory")
	public ServerResponse<String> setCategoryName(Integer categoryId, String categoryName) {
		return categoryService.updateCategoryName(categoryId, categoryName);
	}
}
