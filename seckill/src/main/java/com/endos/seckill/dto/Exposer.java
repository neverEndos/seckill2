package com.endos.seckill.dto;

/**
 * 暴露秒杀URL所需数据
 * Created by Endos on 2017/04/17.
 */
public class Exposer {
    /* 秒杀是否开启 */
    private boolean exposed;
    /* 秒杀ID加密 */
    private String md5;
    /* 秒杀ID */
    private Long seckillId;
    /* 系统时间 */
    private long now;
    /* 秒杀开启时间 */
    private long start;
    /* 秒杀结束时间 */
    private long end;

    /**
     * 秒杀成功构造方法
     * @param exposed
     * @param md5
     * @param seckillId
     */
    public Exposer(boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    /**
     * 秒杀未开启或已经结束构造方法
     * @param exposed
     * @param seckillId
     * @param now
     * @param start
     * @param end
     */
    public Exposer(boolean exposed, Long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    /**
     * 没有对应秒杀记录构造方法
     * @param exposed
     * @param seckillId
     */
    public Exposer(boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
