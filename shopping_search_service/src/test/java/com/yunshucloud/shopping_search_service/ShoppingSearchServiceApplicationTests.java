package com.yunshucloud.shopping_search_service;

import com.yunshucloud.shopping_common.service.SearchService;
import com.yunshucloud.shopping_search_service.service.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShoppingSearchServiceApplicationTests {
	@Autowired
	private SearchServiceImpl searchService;
	@Test
	void contextLoads() {
		List<String> analyze = searchService.analyze("我爱程序员", "ik_pinyin");
		System.out.println(analyze);
	}

}
