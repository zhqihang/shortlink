package com.qihang.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @description: 用户登录入参
 * @author: zhqihang
 * @date: 2024/10/27
 */
@Data
public class UserLoginReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
