package com.qihang.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.admin.dao.entity.GroupDO;

/**
 * @description: 短链接分组接口层
 * @author: zhqihang
 * @date: 2024/10/29
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

}
