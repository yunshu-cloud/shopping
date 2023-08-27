package com.yunshucloud.shopping_goods_service.service;

import com.yunshucloud.shopping_common.exception.BusException;
import com.yunshucloud.shopping_common.pojo.Brand;
import com.yunshucloud.shopping_common.result.CodeEnum;
import com.yunshucloud.shopping_common.service.BrandService;
import com.yunshucloud.shopping_goods_service.mapper.BrandMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService
@Transactional
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
        if (id == 0){
            int i = 1/0; // 模拟系统异常
        }else if (id == -1){
            throw new BusException(CodeEnum.PARAMETER_ERROR); // 模拟业务异常
        }
        return brandMapper.selectById(id);
    }
}
