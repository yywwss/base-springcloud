package com.zhwl.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by lin on 2018/1/29.
 */
public class ResultVo {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Integer status; //响应业务状态  1:成功;  0:失败
    private String msg;// 响应消息
    private Object data;//响应中的数据
    private Integer errorCode;  //失败后的错误码、新增于2018-11-12

    public ResultVo() {
    }

    public ResultVo(Object data) {
        this.status = 1;
        this.msg = "success";
        this.data = data;
    }
    public ResultVo(Integer status, Integer errorCode, String msg, Object data) {
        this.status = status;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVo ok() {
        return ok(null);
    }

    public static ResultVo ok(Object data) {
        return new ResultVo(data);
    }

    public static ResultVo ok(Integer status, String msg, Object data) {
        return new ResultVo(status, null, msg, data);
    }

    //用于即使失败也可以返回数据的场景
    public static ResultVo fail(Integer status, Integer errorCode, String msg, Object data) {
        return new ResultVo(status, errorCode, msg, data);
    }

    //失败只返回消息
    public static ResultVo fail(String msg) {
        return new ResultVo(0, 402, msg, null);
    }

    //失败返回错误码
    public static ResultVo fail(Integer errorCode, String msg) {
        return new ResultVo(0, errorCode, msg, null);
    }

    //失败返回数据
    public static ResultVo fail(Object data) {
        return new ResultVo(0, 402, "error", data);
    }

    public Boolean isOK() {
        return this.status == 1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }



    /**
     * 没有object对象的转化
     */
    public static ResultVo format(String json) {
        try {
            return MAPPER.readValue(json, ResultVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
