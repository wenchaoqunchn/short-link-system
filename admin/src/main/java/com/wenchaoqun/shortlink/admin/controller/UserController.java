package com.wenchaoqun.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.wenchaoqun.shortlink.admin.common.convention.result.Result;
import com.wenchaoqun.shortlink.admin.common.convention.result.Results;
import com.wenchaoqun.shortlink.admin.dto.req.UserLoginReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserActualRespDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserRespDTO;
import com.wenchaoqun.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 */
@RestController
@RequestMapping("/api/short-link/v1/users")  // 使用基础路径
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * 根据用户名获取脱敏用户信息
     */
    @GetMapping("/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名获取真实用户信息
     */
    @GetMapping("/{username}/actual")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 根据用户名检查是否存在
     * @param username 唯一用户名
     * @return 用户存在返回True，不存在返回False
     */
    @GetMapping("/check-exist")
    public Result<Boolean> hasUsername(@RequestParam String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 新增用户
     * @param requestParam 用户记录信息
     */
    @PostMapping
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 修改用户记录
     */
    @PutMapping("/{username}")
    public Result<Void> update(@PathVariable String username,
                               @RequestBody UserUpdateReqDTO requestParam) {
        userService.update(username, requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO){
        return Results.success(userService.login(userLoginReqDTO));
    }

    /**
     * 检查用户是否登录
     */
    @GetMapping("/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }
}