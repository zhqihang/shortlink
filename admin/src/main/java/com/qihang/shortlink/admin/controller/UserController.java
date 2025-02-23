package com.qihang.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.common.convention.result.Results;
import com.qihang.shortlink.admin.dto.req.UserLoginReqDTO;
import com.qihang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.qihang.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.qihang.shortlink.admin.dto.resp.UserActualRespDTO;
import com.qihang.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.qihang.shortlink.admin.dto.resp.UserRespDTO;
import com.qihang.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUserName(@PathVariable("username") String username) {
        /*
        这里手机号需要脱敏
            1.使用 AOP 方法加注解 进行脱敏
            2.Jackson反序列化处理，使用 hutool工具包 + @JsonSerialize注解 （Spring后端对象 --> 前端json）
         */
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户查询用户无脱敏信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUserName(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户是否可用（是否不存在）
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 注册用户
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 修改用户
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam String username, @RequestParam String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    /**
     * 用户退出登录
     */
    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam String username, @RequestParam String token) {
        userService.logout(username, token);
        return Results.success();
    }
}
