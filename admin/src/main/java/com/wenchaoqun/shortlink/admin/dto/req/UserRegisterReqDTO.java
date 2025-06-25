package com.wenchaoqun.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 用户返回参数响应实体
 */
@Data
public class UserRegisterReqDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

}
