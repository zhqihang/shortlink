package com.qihang.shortlink.project.dto.req;

import lombok.Data;

/**
 * @description: 回收站保存功能
 * @author: zhqihang
 * @date: 2024/10/30
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}