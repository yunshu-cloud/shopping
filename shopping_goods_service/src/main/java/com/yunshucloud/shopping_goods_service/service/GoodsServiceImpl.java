package com.yunshucloud.shopping_goods_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunshucloud.shopping_common.pojo.Goods;
import com.yunshucloud.shopping_common.pojo.GoodsImage;
import com.yunshucloud.shopping_common.pojo.Specification;
import com.yunshucloud.shopping_common.pojo.SpecificationOption;
import com.yunshucloud.shopping_common.service.GoodsService;
import com.yunshucloud.shopping_goods_service.mapper.GoodsImageMapper;
import com.yunshucloud.shopping_goods_service.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsImageMapper goodsImageMapper;

    @Override
    public void add(Goods goods) {
        // 插入商品数据
        goodsMapper.insert(goods);

        // 插入商品图片 并写入关联商品ID
        Long goodsId = goods.getId();
        List<GoodsImage> images = goods.getImages();
        for(GoodsImage image : images){
            image.setGoodsId(goodsId);
            goodsImageMapper.insert(image);
        }


        // 插入商品——规格数据
        List<Specification> specifications = goods.getSpecifications();
        ArrayList<SpecificationOption> options = new ArrayList<>();
        for(Specification specification : specifications){
            options.addAll(specification.getSpecificationOptions());
        }

        for (SpecificationOption option : options){
            goodsMapper.addGoodsSpecification(goodsId,option.getId());
        }


    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public Goods findById(Long id) {
        return null;
    }

    @Override
    public void putAway(Long id, Boolean isMarketable) {

    }

    @Override
    public Page<Goods> search(Goods goods, int page, int size) {
        return null;
    }
}
