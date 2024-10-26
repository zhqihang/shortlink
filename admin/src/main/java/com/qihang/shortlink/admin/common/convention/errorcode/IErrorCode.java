package com.qihang.shortlink.admin.common.convention.errorcode;

/**
 * @description: 平台错误码接口
 * @author: zhqihang
 * @date: 2024/10/25
 */
public interface IErrorCode {
    /**
     * 错误码
     */
    String code();

    /**
     * 错误信息
     */
    String message();
}
