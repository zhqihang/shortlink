package com.qihang.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.admin.dao.entity.UserDO;
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
}
