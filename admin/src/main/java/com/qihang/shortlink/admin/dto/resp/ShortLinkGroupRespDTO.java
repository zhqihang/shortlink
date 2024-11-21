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
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 当前分组下短链接数目
     */
    private Integer shortLinkCount;
}
