package com.dreamcc.mall.service.impl;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.entity.Category;
import com.dreamcc.mall.mapper.CategoryMapper;
import com.dreamcc.mall.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	private CategoryMapper categoryMapper;

	@Autowired
	public CategoryServiceImpl(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}


	@Override
	public ServerResponse<List<Category>> getChildrenParallelCategory(Integer parentId) {
		List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(parentId);
		if (CollectionUtils.isEmpty(categoryList)) {
			log.error("not found any node");
		}
		return ServerResponse.createBySuccess(categoryList);
	}
}
