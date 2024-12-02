package com.qihang.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import com.qihang.shortlink.project.dto.req.RecycleBinSaveReqDTO;

/**
 * @description: 回收站管理接口层
 * @author: zhqihang
 * @date: 2024/12/02
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);
}
