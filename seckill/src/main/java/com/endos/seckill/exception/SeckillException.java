package com.endos.seckill.exception;

/**
 * 秒杀相关异常
 * Created by Endos on 2017/04/17.
 */
public class SeckillException extends RuntimeException {
    public SeckillException() {
    }

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeckillException(Throwable cause) {
        super(cause);
    }
}
