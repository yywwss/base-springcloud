package com.zhwl.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
    }
}
