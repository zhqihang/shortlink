package com.qihang.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 短链接创建响应对象
 * @author: zhqihang
 * @date: 2024/10/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortLinkCreateRespDTO {

    /**
     * 分组信息
     */
    private String gid;

    /**
     * 原始链接
     */
    private String originUrl;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
