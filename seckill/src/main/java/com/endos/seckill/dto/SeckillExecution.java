package com.endos.seckill.dto;

import com.endos.seckill.entity.SuccessKilled;
import com.endos.seckill.enums.SeckillStateEnum;

/**
 * 执行秒杀所需数据
 * Created by Endos on 2017/04/17.
 */
public class SeckillExecution {
    /* 秒杀ID */
    private long seckillId;
    /* 秒杀结果状态 */
    private int state;
    /* 秒杀结果信息 */
    private String stateInfo;
    /* 成功秒杀对象 */
    private SuccessKilled successKilled;

    public long getSeckillId() {
        return seckillId;
    }

    /**
     * 成功执行秒杀构造方法，携带SuccessKilled对象
     * @param seckillId
     * @param seckillStateEnum
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 执行秒杀失败构造方法，无对应SuccessKilled对象
     * @param seckillId
     * @param seckillEnum
     */
    public SeckillExecution(long seckillId, SeckillStateEnum seckillEnum) {
        this.seckillId = seckillId;
        this.state = seckillEnum.getState();
        this.stateInfo = seckillEnum.getStateInfo();
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
