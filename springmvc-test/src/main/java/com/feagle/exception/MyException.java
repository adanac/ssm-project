package com.feagle.exception;

/**
 * Created by Feagle on 2017/6/11.
 */
public class MyException extends RuntimeException {

    private String errorCode; //异常编码
    private Object[] values;//其他一些信息
    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MyException(String errorCode, Object[] values) {
        this.errorCode = errorCode;
        this.values = values;
    }

    public MyException(String message, String errorCode, Object[] values) {
        super(message);
        this.errorCode = errorCode;
        this.values = values;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object[] getValues() {
        return values;
    }
}
