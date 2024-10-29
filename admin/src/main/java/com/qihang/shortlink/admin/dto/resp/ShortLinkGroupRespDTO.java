package com.qihang.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @description: 短链接分组排序响应参数
 * @author: zhqihang
 * @date: 2024/10/29
 */
@Data
public class ShortLinkGroupRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}
