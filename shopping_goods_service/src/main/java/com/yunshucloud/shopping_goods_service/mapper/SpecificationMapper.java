package com.yunshucloud.shopping_goods_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yunshucloud.shopping_common.pojo.Specification;

import java.util.List;

public interface SpecificationMapper extends BaseMapper<Specification> {
    Specification findById(Long id);
    // 根据商品类型查询商品规格
    List<Specification> findByProductTypeId(Long productTypeId);
}
