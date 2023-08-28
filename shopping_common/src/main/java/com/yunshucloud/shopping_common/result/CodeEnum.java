package com.yunshucloud.shopping_common.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回状态码枚举类
 */
@Getter
@AllArgsConstructor
public enum CodeEnum {
    // 正常
    SUCCESS(200, "OK"),
    // 系统异常
    SYSTEM_ERROR(500,"系统异常"),
    // 业务异常
    PARAMETER_ERROR(601,"参数异常"),
    INSERT_PRODUCT_TYPE_ERROR(602,"该商品类型不能添加子类型"),
    DELETE_PRODUCT_TYPE_ERROR(603,"该商品有子类型，无法删除")
    ;

    private final Integer code;
    private final String message;
}