package com.feagle.exception;

/**
 * 秒杀相关业务异常
 * Created by feagle on 2017/5/27.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
