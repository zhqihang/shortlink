package com.qihang.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qihang.shortlink.admin.dao.entity.GroupDO;
import com.qihang.shortlink.admin.dao.mapper.GroupMapper;
import com.qihang.shortlink.admin.service.GroupService;
import com.qihang.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 短链接分组接口实现层
 * @author: zhqihang
 * @date: 2024/10/29
 */
@Service
@Slf4j
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {


    @Override
    public void saveGroup(String groupName) {
        // 判断gid是否存在
        String gid;
        do {
            gid = RandomGenerator.generateRandom(); // 生成六位随机数
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .name(groupName)
                .gid(gid)
                .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO 设置用户名
                .eq(GroupDO::getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }
}
