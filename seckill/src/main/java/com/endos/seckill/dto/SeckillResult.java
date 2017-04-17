package com.endos.seckill.dto;

/**
 * 所有ajax请求放回类型，封装json结果
 * Created by Endos on 2017/04/17.
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;

    /**
     * 成功对象模型
     * @param success
     * @param data
     */
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * 失败对象模型
     * @param success
     * @param error
     */
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
