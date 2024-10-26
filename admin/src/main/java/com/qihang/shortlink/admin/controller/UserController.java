package com.qihang.shortlink.admin.controller;

import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.common.convention.result.Results;
import com.qihang.shortlink.admin.dto.resp.UserRespDTO;
import com.qihang.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户管理控制层
 * @author: zhqihang
 * @date: 2024/10/25
 */

@RestController // 组合注解
@RequiredArgsConstructor // 构造器
public class UserController {

    // 构造器方法注入
    private final UserService userService;

    /**
     * 根据用户查询用户信息
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }
}
