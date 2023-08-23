package com.api.shop_project.exception;

public class NotStockException extends RuntimeException {

    public NotStockException() {
        super();
    }

    public NotStockException(String message) {
        super(message);
    }

    public NotStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotStockException(Throwable cause) {
        super(cause);
    }

    protected NotStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
