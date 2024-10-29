package com.qihang.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.qihang.shortlink.admin.common.database.BaseDO;
import lombok.Data;

/**
 * @description: 用户持久层实体类
 * @author: zhqihang
 * @date: 2024/10/25
 */
@Data
@TableName("t_user")
public class UserDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注销时间戳
     */
    private Long deletionTime;
}
