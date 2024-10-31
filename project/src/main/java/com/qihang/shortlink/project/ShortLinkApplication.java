package com.qihang.shortlink.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: project启动类
 * @author: zhqihang
 * @date: 2024/10/30
 */

@SpringBootApplication
@MapperScan("com.qihang.shortlink.project.dao.mapper") // 添加持久层扫描路径
public class ShortLinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkApplication.class, args);
    }
}
