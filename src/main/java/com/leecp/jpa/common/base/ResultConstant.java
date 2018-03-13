package com.leecp.jpa.common.base;

public enum ResultConstant{
    SUCCESS(1,"成功"),
    Fail(0,"失败"),
    USERNAME_EMPTY(1001,"username is empty"),
    PASSWORD_EMPTY(1002,"password is empty"),
    USERNAME_INVALID(1003,"username is invalid"),
    PASWWORD_INVALID(1004,"password is invalid"),
    ACCOUNT_LOCKED(1005,"user had been locked"),
    DATA_LENGTH_INVALID(1006,"data's length is invalid"),
    DATA_EMPTY(1007,"data is empty");
    private int code;
    private String message;
    ResultConstant(int code,String message){
        this.code = code;
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}