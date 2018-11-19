package com.dreamcc.mall.service;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.Category;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-26 11:35
 * @Version: V1.0
 */
public interface ICategoryService {
	/**
	 * gets the child node under the same parent node
	 *
	 * @param parentId
	 * @return
	 */
	ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId);

	/**
	 * add category
	 *
	 * @param category
	 * @return
	 */
	ServerResponse<String> addCategory(Category category);

	/**
	 * update category
	 *
	 * @param categoryId
	 * @param categoryName
	 * @return
	 */
	ServerResponse updateCategoryName(Integer categoryId, String categoryName);

	/**
	 * select category and children by Id
	 *
	 * @param categoryId
	 * @return
	 */
	ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
