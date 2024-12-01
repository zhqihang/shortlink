package com.qihang.shortlink.project.service;

/**
 * @description: URL 标题接口层
 * @author: zhqihang
 * @date: 2024/10/30
 */
public interface UrlTitleService {

    /**
     * 根据 URL 获取标题
     *
     * @param url 目标网站地址
     * @return 网站标题
     */
    String getTitleByUrl(String url);
}
