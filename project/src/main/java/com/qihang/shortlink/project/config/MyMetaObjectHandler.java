package com.qihang.shortlink.project.config;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: 元数据填充
 * @author: zhqihang
 * @date: 2024/10/31
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开始插入填充...");
        this.strictInsertFill(metaObject, "createTime", Date.class, DateTime.now());
        this.strictInsertFill(metaObject, "updateTime", Date.class, DateTime.now());
        this.strictInsertFill(metaObject, "delFlag", Integer.class, 0); // 删除标记
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开始更新填充...");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, DateTime.now());
    }
}
