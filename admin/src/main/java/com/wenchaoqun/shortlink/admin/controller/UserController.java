package com.wenchaoqun.shortlink.admin.controller;

import com.wenchaoqun.shortlink.admin.common.convention.exception.ClientException;
import com.wenchaoqun.shortlink.admin.common.convention.result.Result;
import com.wenchaoqun.shortlink.admin.common.convention.result.Results;
import com.wenchaoqun.shortlink.admin.common.enums.UserErrorCodeEnum;
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
        UserRespDTO result = userService.getUserByUsername(username);
        if (result == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NOT_EXIST);
        }
        else{
            return Results.success(result);
        }
    }
}
