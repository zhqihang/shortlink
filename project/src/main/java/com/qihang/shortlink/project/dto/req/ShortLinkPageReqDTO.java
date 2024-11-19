package com.qihang.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qihang.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * @description: 短连接分页请求参数
 * @author: zhqihang
 * @date: 2024/11/19
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;
}