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
    PARAMETER_ERROR(601,"参数异常")
    ;

    private final Integer code;
    private final String message;
}