package com.qihang.shortlink.project.controller;

import com.qihang.shortlink.project.common.convention.result.Result;
import com.qihang.shortlink.project.common.convention.result.Results;
import com.qihang.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.qihang.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 回收站管理控制层
 * @author: zhqihang
 * @date: 2024/12/02
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * 保存回收站
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }
}
