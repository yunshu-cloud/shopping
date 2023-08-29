package com.yunshucloud.shopping_goods_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshucloud.shopping_common.pojo.Goods;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper extends BaseMapper<Goods> {
    // 添加商品_规格数据
    void addGoodsSpecification(@Param("gid") Long gid,@Param("optionId")Long optionId);
}
