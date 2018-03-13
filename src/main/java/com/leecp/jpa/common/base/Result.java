package com.leecp.jpa.common.base;

import java.io.Serializable;

/**
 * 统一返回结果
 * statusCode message data
 *
 */
public class Result implements Serializable{
    private Status status;
    private String message;
    private Object data;
    public Result (Status status){
        this.status = status;
    }
    public Result (String message) {this.message = message;}
    public Result(Status status,String message,Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public enum Status{
        SUCCESS,ERROR;
    }
    public static Result ok(){
        return new Result(Status.SUCCESS);
    }
    public static Result ok(String message) {return new Result(message);}
    public static Result ok(String message,Object data){
        return new Result(Status.SUCCESS,message,data);
    }
    public static Result err(){
        return new Result(Status.ERROR);
    }
    public static Result err(String message) {return new Result(message);}
    public static Result err(String message,Object data){
        return new Result(Status.ERROR,message,data);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
