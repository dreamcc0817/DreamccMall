package com.dreamcc.mall;

import com.dreamcc.mall.common.ServerResponse;
import com.dreamcc.mall.dao.ProductDao;
import com.dreamcc.mall.entity.Product;
import com.dreamcc.mall.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTests {

	private static final String REST_URL_PREFIX = "http://localhost:9099/dreamccmall";
	@Autowired
	private IProductService productService;

	@Autowired
	private ProductDao productDao;

//	@Autowired
//	RestTemplate restTemplate;

	@Test
	public void testProductDAO() {
		//List<ProductListVo> list=restTemplate.getForObject(REST_URL_PREFIX + "/product/getProductList", List.class);
		List<Product> list=productDao.getProductList();
		for (Product productListVo : list) {
			System.out.println("productListVo = " + productListVo);
		}
	}
	@Test
	public void testProcutService(){
		ServerResponse<PageInfo> list = productService.getProductList(1, 10);
		System.out.println(list);
	}
}
