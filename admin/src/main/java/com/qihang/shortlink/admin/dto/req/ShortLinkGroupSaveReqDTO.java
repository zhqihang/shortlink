package com.qihang.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @description: 短链接分组创建参数
 * @author: zhqihang
 * @date: 2024/10/29
 */
@Data
public class ShortLinkGroupSaveReqDTO {

    /**
     * 分组名称
     */
    private String name;
}
