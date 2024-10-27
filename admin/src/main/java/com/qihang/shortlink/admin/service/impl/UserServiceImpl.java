package com.qihang.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.shortlink.admin.common.convention.exception.ClientException;
import com.qihang.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.qihang.shortlink.admin.dao.entity.UserDO;
import com.qihang.shortlink.admin.dao.mapper.UserMapper;
import com.qihang.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.qihang.shortlink.admin.dto.resp.UserRespDTO;
import com.qihang.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @description: 用户接口实现层
 * @author: zhqihang
 * @date: 2024/10/25
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    // 构造器注入
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;

    @Override
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

    @Override
    public Boolean hasUsername(String username) {
        // LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class).eq(UserDO::getUsername, username);
        // UserDO userDO = baseMapper.selectOne(queryWrapper);
        // return userDO == null;
        // 布隆过滤器方式
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        // 用户名已存在 抛出异常提示信息
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXIST);
        }
        int insert = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
        // 如果插入失败 抛出异常提示信息
        if (insert < 1) {
            throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
        }
        // 插入成功 写入布隆过滤器
        userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
    }
}
