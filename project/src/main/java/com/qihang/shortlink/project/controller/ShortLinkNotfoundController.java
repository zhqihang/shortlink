package com.qihang.shortlink.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 短链接不存在 跳转控制器
 * @author: zhqihang
 * @date: 2024/12/01
 */
@Controller
public class ShortLinkNotfoundController {

    /**
     * 短链接不存在跳转页面
     */
    @RequestMapping("/page/notfound")
    public String notfound() {
        return "notfound";
    }
}