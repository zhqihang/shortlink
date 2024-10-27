package com.qihang.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @description: 用户更改入参
 * @author: zhqihang
 * @date: 2024/10/27
 */
@Data
public class UserUpdateReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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