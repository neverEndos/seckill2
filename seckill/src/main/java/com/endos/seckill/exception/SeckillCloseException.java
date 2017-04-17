package com.endos.seckill.exception;

/**
 * 秒杀关闭异常
 * Created by Endos on 2017/04/17.
 */
public class SeckillCloseException extends RuntimeException {
    public SeckillCloseException() {
    }

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillCloseException(Throwable cause) {
        super(cause);
    }
}
