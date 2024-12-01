package com.qihang.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import com.qihang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.qihang.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.qihang.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.qihang.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

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

    /**
     * 修改短链接信息
     *
     * @param requestParam 修改短链接请求参数
     * @return
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 分页查询短连接
     *
     * @param requestParam 分页查询参数
     * @return 分页查询结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组内数量
     *
     * @param requestParam 查询短链接分组内数量请求参数
     * @return 查询短链接分组内数量响应
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}
