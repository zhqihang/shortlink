package com.qihang.shortlink.admin.controller;

import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.common.convention.result.Results;
import com.qihang.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.qihang.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     *
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
}
