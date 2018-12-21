package com.dreamcc.mall.user.server.service.impl;

import com.dreamcc.mall.common.common.ServerResponse;
import com.dreamcc.mall.user.common.entity.Category;
import com.dreamcc.mall.user.server.dao.CategoryDao;
import com.dreamcc.mall.user.server.service.ICategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-26 11:40
 * @Version: V1.0
 */
@Slf4j
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	@Override
	public ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId) {
		List<Category> categoryList = categoryDao.selectCategoryChildrenByParentId(parentId);
		return CollectionUtils.isEmpty(categoryList) ? ServerResponse.createBySuccess(categoryList) : ServerResponse.createByErrorMessage("not found any node");
	}

	@Override
	public ServerResponse<String> addCategory(Category category) {
		return categoryDao.insert(category) > 1 ? ServerResponse.createBySuccess("add category success") : ServerResponse.createByErrorMessage("failed to add category");
	}

	@Override
	public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
		Category category = new Category(categoryId, categoryName);
		return categoryDao.updateByPrimaryKeySelective(category) > 1 ? ServerResponse.createBySuccess("update category success") : ServerResponse.createByErrorMessage("failed to update category");
	}

	@Override
	public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId) {
		Set<Category> categorySet = Sets.newHashSet();
		findChildCategory(categorySet, categoryId);


		List<Integer> categoryIdList = Lists.newArrayList();
		if (categoryId != null) {
			for (Category categoryItem : categorySet) {
				categoryIdList.add(categoryItem.getId());
			}
		}
		return ServerResponse.createBySuccess(categoryIdList);
	}

	private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
		Category category = categoryDao.selectByPrimaryKey(categoryId);
		if (category != null) {
			categorySet.add(category);
		}
		List<Category> categoryList = categoryDao.selectCategoryChildrenByParentId(categoryId);
		for (Category categoryItem : categoryList) {
			findChildCategory(categorySet, categoryItem.getId());
		}
		return categorySet;
	}
}
