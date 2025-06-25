package com.wenchaoqun.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wenchaoqun.shortlink.admin.dao.entity.UserDO;
import com.wenchaoqun.shortlink.admin.dto.req.UserLoginReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.wenchaoqun.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.wenchaoqun.shortlink.admin.dto.resp.UserRespDTO;

public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 根据用户名检查是否存在
     * @param username 唯一用户名
     * @return 用户存在返回True，不存在返回False
     */
    Boolean hasUsername(String username);

    /**
     * 新增用户
     * @param requestParam 用户记录信息
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 修改用户记录
     * @param username 唯一用户名
     * @param requestParam 需要修改的用户记录信息
     */
    void update(String username, UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     * @param requestParam 用户登录请求参数
     * @return 用户Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     * @param username 用户名
     * @param token 用户Token
     * @return 登录返回True，没登录返回False
     */
    Boolean checkLogin(String username, String token);

    /**
     * 用户退出登录
     *
     * @param username 用户名
     */
    void logout(String username, String token);
}
