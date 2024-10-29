package com.qihang.shortlink.admin.controller;

import com.qihang.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 短链接分组控制层
 * @author: zhqihang
 * @date: 2024/10/29
 */

@RestController
@RequiredArgsConstructor
public class GroupController {

    // 依赖注入
    private final GroupService groupService;


}
