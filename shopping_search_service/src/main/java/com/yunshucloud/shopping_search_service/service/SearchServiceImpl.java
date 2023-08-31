package com.yunshucloud.shopping_search_service.service;

import com.yunshucloud.shopping_common.pojo.GoodsDesc;
import com.yunshucloud.shopping_common.pojo.GoodsSearchParam;
import com.yunshucloud.shopping_common.pojo.GoodsSearchResult;
import com.yunshucloud.shopping_common.service.SearchService;
import lombok.SneakyThrows;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 分词
     * @param text 被分词的文本
     * @param analyzer 分词器
     * @return 分词结果
     */
    @SneakyThrows // 抛出已检查异常
    public List<String> analyze(String text, String analyzer){
        // 分词请求
        AnalyzeRequest request = AnalyzeRequest.withIndexAnalyzer("goods",analyzer, text);
        // 分词响应
        AnalyzeResponse response = restHighLevelClient.indices().analyze(request, RequestOptions.DEFAULT);
        // 分词结果集合
        List<String> words = new ArrayList<>();
        // 处理响应
        List<AnalyzeResponse.AnalyzeToken> tokens = response.getTokens();
        for (AnalyzeResponse.AnalyzeToken token : tokens) {
            String term = token.getTerm(); // 分出的词
            words.add(term);
        }
        return words;
    }

    @Override
    public List<String> autoSuggest(String keyword) {
        return null;
    }

    @Override
    public GoodsSearchResult search(GoodsSearchParam goodsSearchParam) {
        return null;
    }

    @Override
    public void syncGoodsToES(GoodsDesc goodsDesc) {

    }

    @Override
    public void delete(Long id) {

    }
}
