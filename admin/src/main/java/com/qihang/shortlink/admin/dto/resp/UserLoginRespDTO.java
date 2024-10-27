package com.qihang.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户登录返回参数
 * @author: zhqihang
 * @date: 2024/10/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRespDTO {

    /**
     * 用户 Token
     */
    private String token;
}
