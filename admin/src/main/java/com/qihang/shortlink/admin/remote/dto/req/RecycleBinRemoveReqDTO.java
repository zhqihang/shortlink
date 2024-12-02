package com.qihang.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * @description: 回收站移除功能
 * @author: zhqihang
 * @date: 2024/12/02
 */
@Data
public class RecycleBinRemoveReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
