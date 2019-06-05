package com.zhwl.controller.book;

import com.zhwl.exception.BaseException;
import com.zhwl.result.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;

/**
 * SpringBoot全局统一异常处理
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo errorHandler(Exception e) {
        e.printStackTrace();
        return ResultVo.fail(e.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 BaseException.class
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResultVo myErrorHandler(HttpServletResponse response,BaseException e) {
        response.setStatus(e.getCode());
        e.printStackTrace();
        return ResultVo.fail(e.getMessage());
    }
}
