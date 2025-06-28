package com.wenchaoqun.shortlink.admin.controller;

import com.wenchaoqun.shortlink.admin.common.convention.result.Result;
import com.wenchaoqun.shortlink.admin.common.convention.result.Results;
import com.wenchaoqun.shortlink.admin.dto.req.GroupAddReqDTO;
import com.wenchaoqun.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/short-link/v1/groups")
public class GroupController {
    private final GroupService groupService;

    /**
     * 新增短链接分组
     * @param requestParam
     * @return
     */
    @PostMapping()
    public Result<Void> addGroup(@RequestBody GroupAddReqDTO requestParam){
        groupService.addGroup(requestParam.getGroupName());
        return Results.success(null);
    }
}
