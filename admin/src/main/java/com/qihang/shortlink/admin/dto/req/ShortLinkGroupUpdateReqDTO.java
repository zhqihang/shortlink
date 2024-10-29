package com.qihang.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @description: 短链接分组修改参数
 * @author: zhqihang
 * @date: 2024/10/29
 */
@Data
public class ShortLinkGroupUpdateReqDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;
}
