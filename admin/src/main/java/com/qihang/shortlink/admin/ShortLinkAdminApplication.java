package com.qihang.shortlink.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: admin启动类
 * @author: zhqihang
 * @date: 2024/10/25
 */

@SpringBootApplication
@MapperScan("com.qihang.shortlink.admin.dao.mapper") // 添加持久层扫描路径
public class ShortLinkAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkAdminApplication.class, args);
    }
}
