package com.qihang.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * @description: 短链接分组查询返回参数
 * @author: zhqihang
 * @date: 2024/11/21
 */

@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;

}
