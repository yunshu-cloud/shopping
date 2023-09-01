package com.yunshucloud.shopping_search_customer_api.controller;

import com.yunshucloud.shopping_common.result.BaseResult;
import com.yunshucloud.shopping_common.service.SearchService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户商品搜索
 */
@RestController
@RequestMapping("/user/goodsSearch")
public class GoodsSearchController {
    @DubboReference
    private SearchService searchService;




    /**
     * 自动补齐关键字
     *
     * @param keyword 被补齐的词
     * @return 补齐的关键词集合
     */
    @GetMapping("/autoSuggest")
    public BaseResult<List<String>> autoSuggest(String keyword) {
        List<String> keywords = searchService.autoSuggest(keyword);
        return BaseResult.ok(keywords);
    }



}
