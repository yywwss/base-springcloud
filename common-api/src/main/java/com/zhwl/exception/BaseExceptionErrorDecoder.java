package com.zhwl.exception;

import com.alibaba.fastjson.JSON;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

@Configuration
public class BaseExceptionErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String errorContent = Util.toString(response.body().asReader());
            return JSON.parseObject(errorContent, BaseException.class);
        } catch (IOException e) {
            return new BaseException(500, "unknown error");
        }

        //原来的实现 2019-06-05
        /*try {
            String ExceptionJson = Util.toString(response.body().asReader());
            Map<String,Object> jsonObject = JSON.parseObject(ExceptionJson);
            return new BaseException((String)jsonObject.get("message"));
            *//*if(jsonObject.containsKey("errorCode")) { //自己系统抛出的错误
                return new BaseException((int)jsonObject.get("errorCode"),(String)jsonObject.get("msg"));
            }else{
                RequestException exceptionInfo = JSON.parseObject(ExceptionJson, RequestException.class);
                Class clazz = Class.forName(exceptionInfo.getException());
                return (Exception) clazz.getDeclaredConstructor(String.class).newInstance(exceptionInfo.getMessage());
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FeignException.errorStatus(methodKey, response);*/
    }
}
