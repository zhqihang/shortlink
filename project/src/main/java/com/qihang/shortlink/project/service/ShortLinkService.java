package com.qihang.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import com.qihang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @description: 短链接接口层
 * @author: zhqihang
 * @date: 2024/10/30
 */
public interface ShortLinkService extends IService<ShortLinkDO> {


    /**
     * 创建短链接
     *
     * @param requestParam 创建短链接请求参数
     * @return 短链接信息
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}
