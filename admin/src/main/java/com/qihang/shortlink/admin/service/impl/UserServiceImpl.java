package com.qihang.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.shortlink.admin.common.convention.exception.ClientException;
import com.qihang.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.qihang.shortlink.admin.dao.entity.UserDO;
import com.qihang.shortlink.admin.dao.mapper.UserMapper;
import com.qihang.shortlink.admin.dto.resp.UserRespDTO;
import com.qihang.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description: 用户接口实现层
 * @author: zhqihang
 * @date: 2024/10/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
