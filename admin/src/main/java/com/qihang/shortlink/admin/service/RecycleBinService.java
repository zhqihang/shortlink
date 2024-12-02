package com.qihang.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.qihang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * @description: URL 回收站接口层
 * @author: zhqihang
 * @date: 2024/12/02
 */

public interface RecycleBinService {

    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}