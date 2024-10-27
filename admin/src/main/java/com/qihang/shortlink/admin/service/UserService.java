package com.qihang.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.admin.dao.entity.UserDO;
import com.qihang.shortlink.admin.dto.req.UserLoginReqDTO;
import com.qihang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.qihang.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.qihang.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.qihang.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @description: 用户接口层
 * @author: zhqihang
 * @date: 2024/10/25
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否可用
     *
     * @param username
     * @return 用户名存在返回 false 不存在返回 true
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     *
     * @param requestParam 注册请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名修改用户信息
     *
     * @param requestParam 修改请求参数
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     *
     * @param requestParam 用户登录请求参数
     * @return 用户登录返回参数
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     *
     * @param token 用户登录 Token
     * @return 用户登录状态
     */
    Boolean checkLogin(String username, String token);

}
