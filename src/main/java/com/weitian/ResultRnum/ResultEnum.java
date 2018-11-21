package com.weitian.ResultRnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Administrator on 2018/11/21.
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    USER_NOT_EXISTS(40,"用户不存在"),
    LOGIN_IS_ERROR(41,"用户名或密码不正确"),
    ;
    private Integer code;
    private String msg;

}
