package com.zhwl.exception;

import com.zhwl.result.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResultVo myErrorHandler(BaseException e) {
        e.printStackTrace();
        return ResultVo.fail(e.getMessage());
    }
}
