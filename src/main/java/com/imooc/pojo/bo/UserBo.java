package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户对象BO",description = "客户端传入")
public class UserBo {
    @ApiModelProperty(value = "用户名",name = "username",example = "cgl")
    private String username;
    @ApiModelProperty(value = "密码",name = "password",example = "123678")
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example = "123456")
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
