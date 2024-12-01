package com.qihang.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 短链接跳转实体
 * @author: zhqihang
 * @date: 2024/12/01
 */

@Data
@TableName("t_link_goto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShortLinkGotoDO {

    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
