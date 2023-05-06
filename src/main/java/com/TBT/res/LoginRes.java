package com.TBT.res;

/**
 * @author xjy
 * @create 2023-05-04 17:22
 */
public class LoginRes {

    private Integer code;
    private String token;

    public LoginRes() {
    }

    public LoginRes(Integer code, String token) {
        this.code = code;
        this.token = token;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
