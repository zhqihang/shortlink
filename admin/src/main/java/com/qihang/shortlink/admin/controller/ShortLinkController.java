package com.qihang.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.common.convention.result.Results;
import com.qihang.shortlink.admin.remote.ShortLinkRemoteService;
import com.qihang.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.qihang.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.qihang.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.qihang.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.qihang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 短链接后管控制层
 * @author: zhqihang
 * @date: 2024/11/19
 */

@RestController
public class ShortLinkController {

    //TODO 后续重构为 SpringCloud Feign 调用，此为接口注入
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 修改短链接信息
     */
    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
