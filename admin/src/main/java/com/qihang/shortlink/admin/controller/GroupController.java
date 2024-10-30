package com.qihang.shortlink.admin.controller;

import com.qihang.shortlink.admin.common.convention.result.Result;
import com.qihang.shortlink.admin.common.convention.result.Results;
import com.qihang.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.qihang.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.qihang.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.qihang.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 新增短链接分组
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询短链接分组集合
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改短链接分组
     *
     * @param requestParam
     * @return
     */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组
     *
     * @param gid 短链接分组标识
     * @return
     */
    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> deleteGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }
}
