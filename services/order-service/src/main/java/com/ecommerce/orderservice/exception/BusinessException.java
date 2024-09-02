package com.ecommerce.orderservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {
    private final String msg;

    public BusinessException(String msg) {
        this.msg = msg;
    }

    public BusinessException(String message, String msg) {
        super(message);
        this.msg = msg;
    }

    public BusinessException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public BusinessException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
