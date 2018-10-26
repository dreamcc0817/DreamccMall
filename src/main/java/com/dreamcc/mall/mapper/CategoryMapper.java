package com.dreamcc.mall.mapper;

import com.dreamcc.mall.entity.Category;

import java.util.List;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.mapper
 * @Description:
 * @Author: dreamcc
 * @Date: 2018-10-26 10:55
 * @Version: V1.0
 */
public interface CategoryMapper {
	/**
	 * delete category by primary key
	 *
	 * @return
	 */
	int deleteByPrimaryKey();

	/**
	 * add category info
	 *
	 * @param category
	 * @return
	 */
	int insert(Category category);

	int insertSelective(Category record);

	Category selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Category record);

	int updateByPrimaryKey(Category record);

	List<Category> selectCategoryChildrenByParentId(Integer parentId);
}
