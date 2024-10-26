package com.qihang.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @description: 用户返回参数响应--无脱敏
 * @author: zhqihang
 * @date: 2024/10/25
 */
@Data
public class UserActualRespDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
