package com.qihang.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import com.qihang.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.qihang.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkPageRespDTO;

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

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 分页查询参数
     * @return 分页查询结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
