package com.endos.seckill.service.impl;

import com.endos.seckill.dao.SeckillDao;
import com.endos.seckill.entity.Seckill;
import com.endos.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Endos on 2017/04/14.
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    private SeckillDao seckillDao;

    /**
     * 默认查询5条记录
     * @return
     */
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 5);
    }

    public Seckill getById(Long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public void exportSeckillUrl(Long seckillId) {

    }

    public void executeSeckill(Long seckillId, Long userPhone, String md5) {

    }
}
