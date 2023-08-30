package com.yunshucloud.shopping_manager_api.controller;

import com.yunshucloud.shopping_common.pojo.Goods;
import com.yunshucloud.shopping_common.result.BaseResult;
import com.yunshucloud.shopping_common.service.GoodsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * 商品
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @DubboReference
    private GoodsService goodsService;

    @PostMapping("/add")
    public BaseResult add(@RequestBody Goods goods){
        goodsService.add(goods);
        return BaseResult.ok();
    }

    /**
     * 修改商品
     *
     * @param goods 商品实体
     * @return 执行结果
     */
    @PutMapping("/update")
    public BaseResult update(@RequestBody Goods goods) {
        goodsService.update(goods);
        return BaseResult.ok();
    }

}
