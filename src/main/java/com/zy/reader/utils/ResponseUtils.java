package com.zy.reader.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {
    private String code;
    private String message;
    private Map data = new LinkedHashMap();

    //成功响应返回0
    public ResponseUtils() {
        this.code = "0";
        this.message = "SUCCESS";
    }

    //失败返回错误信息
    public ResponseUtils(String code,String message){
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ResponseUtils put(String key,Object value){
        this.data.put(key,value);
        return this;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }
}
