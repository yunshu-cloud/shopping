package com.yunshucloud.shopping_manager_api.controller;

import com.yunshucloud.shopping_common.pojo.Brand;
import com.yunshucloud.shopping_common.result.BaseResult;
import com.yunshucloud.shopping_common.service.BrandService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand")
public class BrandController {
    // 远程注入
    @DubboReference
    private BrandService brandService;


    @GetMapping("/findById")
    public BaseResult<Brand> findById(Long id){
        Brand brand = brandService.findById(id);
        return BaseResult.ok(brand);
    }
}

