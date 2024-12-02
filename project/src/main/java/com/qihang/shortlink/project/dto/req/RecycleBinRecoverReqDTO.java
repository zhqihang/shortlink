package com.qihang.shortlink.project.dto.req;

import lombok.Data;

/**
 * @description: 回收站恢复功能
 * @author: zhqihang
 * @date: 2024/12/02
 */
@Data
public class RecycleBinRecoverReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
