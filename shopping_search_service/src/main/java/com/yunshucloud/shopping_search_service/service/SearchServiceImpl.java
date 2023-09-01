package com.yunshucloud.shopping_search_service.service;

import com.yunshucloud.shopping_common.pojo.*;
import com.yunshucloud.shopping_common.service.SearchService;
import com.yunshucloud.shopping_search_service.repository.GoodsESRepository;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.AnalyzeRequest;
import org.elasticsearch.client.indices.AnalyzeResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.suggest.Suggest;

import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.SuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DubboService
public class SearchServiceImpl implements SearchService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private GoodsESRepository goodsESRepository;

    @Autowired
    private ElasticsearchRestTemplate template;

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

    // 自动补齐
    @Override
    public List<String> autoSuggest(String keyword) {
        // 1.创建补全条件
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        SuggestionBuilder suggestionBuilder = SuggestBuilders
                .completionSuggestion("tags")
                .prefix(keyword)
                .skipDuplicates(true)
                .size(10);


        suggestBuilder.addSuggestion("prefix_suggestion", suggestionBuilder);


        // 2.发送请求
        SearchResponse response = template.suggest(suggestBuilder, IndexCoordinates.of("goods"));


        // 3.处理结果
        List<String> result = response
                .getSuggest()
                .getSuggestion("prefix_suggestion")
                .getEntries()
                .get(0)
                .getOptions()
                .stream()
                .map(Suggest.Suggestion.Entry.Option::getText)
                .map(Text::toString)
                .collect(Collectors.toList());


        return result;
    }



    @Override
    public GoodsSearchResult search(GoodsSearchParam goodsSearchParam) {
        return null;
    }

    // 向ES同步商品数据
    @Override
    public void syncGoodsToES(GoodsDesc goodsDesc) {
        // 将商品详情对象转为GoodsES对象
        GoodsES goodsES = new GoodsES();
        goodsES.setId(goodsDesc.getId());
        goodsES.setGoodsName(goodsDesc.getGoodsName());
        goodsES.setCaption(goodsDesc.getCaption());
        goodsES.setPrice(goodsDesc.getPrice());
        goodsES.setHeaderPic(goodsDesc.getHeaderPic());
        goodsES.setBrand(goodsDesc.getBrand().getName());
        // 类型集合
        List<String> productType = new ArrayList();
        productType.add(goodsDesc.getProductType1().getName());
        productType.add(goodsDesc.getProductType2().getName());
        productType.add(goodsDesc.getProductType3().getName());
        goodsES.setProductType(productType);
        // 规格集合
        Map<String,List<String>> map = new HashMap();
        List<Specification> specifications = goodsDesc.getSpecifications();
        // 遍历规格
        for (Specification specification : specifications) {
            // 规格项集合
            List<SpecificationOption> options = specification.getSpecificationOptions();
            // 规格项名集合
            List<String> optionStrList = new ArrayList();
            for (SpecificationOption option : options) {
                optionStrList.add(option.getOptionName());
            }
            map.put(specification.getSpecName(),optionStrList);
        }
        goodsES.setSpecification(map);
        // 关键字
        List<String> tags = new ArrayList();
        tags.add(goodsDesc.getBrand().getName()); //品牌名是关键字
        tags.addAll(analyze(goodsDesc.getGoodsName(),"ik_smart"));//商品名分词后为关键词
        tags.addAll(analyze(goodsDesc.getCaption(),"ik_smart"));//副标题分词后为关键词
        goodsES.setTags(tags);

        goodsESRepository.save(goodsES);
    }


    @Override
    public void delete(Long id) {

    }
}
