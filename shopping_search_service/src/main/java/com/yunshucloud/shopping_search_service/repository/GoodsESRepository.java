package com.yunshucloud.shopping_search_service.repository;

import com.yunshucloud.shopping_common.pojo.GoodsES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsESRepository extends ElasticsearchRepository<GoodsES,Long> {




}
