package com.yunshucloud.shopping_search_service;

import com.yunshucloud.shopping_common.pojo.GoodsDesc;
import com.yunshucloud.shopping_common.service.GoodsService;
import com.yunshucloud.shopping_common.service.SearchService;
import com.yunshucloud.shopping_search_service.service.SearchServiceImpl;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShoppingSearchServiceApplicationTests {
	@Autowired
	private SearchServiceImpl searchService;

	@DubboReference
	private GoodsService goodsService;

	private static ZooKeeper zooKeeper;
	@Test
	void contextLoads() {
		List<String> analyze = searchService.analyze("我爱程序员", "ik_pinyin");
		System.out.println(analyze);
	}

	@Test
	void testSyncGoodsToES(){
		List<GoodsDesc> goods = goodsService.findAll();
		for (GoodsDesc goodsDesc : goods) {
			// 如果商品是上架状态
			if (goodsDesc.getIsMarketable()){
				searchService.syncGoodsToES(goodsDesc);
			}
		}
	}


	@Test
	void testZookeeper(){
		String host = "192.168.66.100:2181";
		int sessionTimeout = 10000;
		try {
			zooKeeper = new ZooKeeper(host,sessionTimeout, null);
			System.out.println("Connected to Zookeeper server!");
		} catch (Exception e){
			System.out.println("Failed to connect to Zookeeper server");
			e.printStackTrace();
		}
	}


	@Test
	void testAutoSuggest(){
		List<String> i = searchService.autoSuggest("苹果");
		System.out.println(i);
	}

}
