package com.dreamcc.mall.user.client;

import com.dreamcc.mall.entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title: IDEAProject
 * @Package: com.dreamcc.mall.user.client
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/12/13 15:37
 * @Version: V1.0
 */
@FeignClient("category")
public interface CategoryClient {
	@GetMapping
	Category selectByPrimaryKey(int categoryId);
}
