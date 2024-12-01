package com.qihang.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @description: 有效期类型
 * @author: zhqihang
 * @date: 2024/11/24
 */

@Getter
@RequiredArgsConstructor
public enum VaildDateTypeEnum {

    /**
     * 永久有效期
     */
    PERMANENT(0),

    /**
     * 自定义有效期
     */
    CUSTOM(1);

    private final int type;
}
