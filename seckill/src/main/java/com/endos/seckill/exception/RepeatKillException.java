package com.endos.seckill.exception;

/**
 * 重复秒杀异常
 * Created by Endos on 2017/04/17.
 */
public class RepeatKillException extends RuntimeException{
    public RepeatKillException() {
    }

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatKillException(Throwable cause) {
        super(cause);
    }
}
