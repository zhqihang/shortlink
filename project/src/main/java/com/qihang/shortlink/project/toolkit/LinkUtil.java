package com.qihang.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Optional;

import static com.qihang.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * @description: 短链接工具类
 * @author: zhqihang
 * @date: 2024/12/01
 */
public class LinkUtil {

    /**
     * 获取短链接缓存有效期时间
     *
     * @param validDate 有效期时间
     * @return 有限期时间戳
     */
    public static long getLinkCacheValidTime(Date validDate) {
        return Optional.ofNullable(validDate)
                // 存在有效期  有效期和当前时间进行对比 获取毫秒差值
                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
                // 存在有效期 永久有效 即默认值
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }
}
