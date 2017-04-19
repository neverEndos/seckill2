package com.endos.seckill.service.impl;

import com.endos.seckill.dao.SeckillDao;
import com.endos.seckill.dao.SuccessKilledDao;
import com.endos.seckill.dao.cache.RedisDao;
import com.endos.seckill.dto.Exposer;
import com.endos.seckill.dto.SeckillExecution;
import com.endos.seckill.entity.Seckill;
import com.endos.seckill.entity.SuccessKilled;
import com.endos.seckill.enums.SeckillStateEnum;
import com.endos.seckill.exception.RepeatKillException;
import com.endos.seckill.exception.SeckillCloseException;
import com.endos.seckill.exception.SeckillException;
import com.endos.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 秒杀service接口实现类
 * Created by Endos on 2017/04/14.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    // MD5加密盐
    private final static String SLAT = "QWER1234qwer";

    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    /**
     * 默认查询5条记录
     * @return
     */
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 5);
    }

    /**
     * 暴露秒杀URL
     * @param seckillId
     */
    public Exposer exportSeckillUrl(Long seckillId) {
        Seckill seckill = getById(seckillId);
        // 没有对应秒杀
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        // 秒杀未开启或者秒杀已结束
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (startTime.after(nowTime) || endTime.before(nowTime)) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }

        // 秒杀正常，加密seckillId
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 根据ID查询对应秒杀记录
     * @param seckillId
     * @return
     */
    public Seckill getById(Long seckillId) {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill != null) {
                redisDao.putSeckill(seckill);
            }
        }
        return seckill;
    }

    /**
     * MD5加密
     * @param seckillId
     * @return
     */
    private static String getMD5(Long seckillId) {
        String base = SLAT + seckillId;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("数据被篡改！");
        }
        try {
            Date killTime = new Date();
            // 执行秒杀逻辑：减库存+记录购买行为
            int reduceResult = seckillDao.reduceNumber(seckillId, killTime);
            if (reduceResult <= 0) {
                throw new SeckillCloseException("秒杀结束！");
            }
            // 记录购买行为
            int insertResult = successKilledDao.insertSuccessSeckilled(seckillId, userPhone);
            if (insertResult <= 0) {
                throw new RepeatKillException("重复秒杀！");
            }
            // 查询秒杀结果，并携带秒杀对象
            SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
            return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            throw new SeckillException("秒杀异常：" + e.getMessage());
        }
    }
}
