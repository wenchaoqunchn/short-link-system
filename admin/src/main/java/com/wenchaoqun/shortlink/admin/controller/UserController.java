package com.wenchaoqun.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.wenchaoqun.shortlink.admin.common.convention.result.Result;
import com.wenchaoqun.shortlink.admin.common.convention.result.Results;
import com.wenchaoqun.shortlink.admin.dto.resp.UserActualRespDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserRespDTO;
import com.wenchaoqun.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/shortlink/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username),UserActualRespDTO.class));
    }

}
