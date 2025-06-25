package com.wenchaoqun.shortlink.admin.common.enums;

import com.wenchaoqun.shortlink.admin.common.convention.errorcode.IErrorCode;

public enum UserErrorCodeEnum implements IErrorCode {

    USER_NOT_EXIST("B000200", "用户记录不存在"),
    USER_NAME_REPEAT("B000201", "用户名已存在"),
    USER_EXIST("B000202", "用户记录存在"),
    USER_SAVE_ERROR("B000203", "用户记录新增失败"),
    USER_UPDATE_ERROR("B000204", "用户记录更新失败"),
    USER_LOGIN_ERROR("B000205","用户登录失败");


    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
