package com.yunshucloud.shopping_goods_service.service;

import com.yunshucloud.shopping_common.pojo.Brand;
import com.yunshucloud.shopping_common.service.BrandService;
import com.yunshucloud.shopping_goods_service.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @Override
    public Brand findById(Long id) {
        return brandMapper.selectById(id);
    }
}
