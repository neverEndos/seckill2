package com.endos.seckill.service;

import com.endos.seckill.dto.Exposer;
import com.endos.seckill.dto.SeckillExecution;
import com.endos.seckill.entity.Seckill;

import java.util.List;

/**
 * 秒杀service层接口
 * Created by Endos on 2017/04/14.
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(Long seckillId);

    Exposer exportSeckillUrl(Long seckillId);

    SeckillExecution executeSeckill(Long seckillId, Long userPhone, String md5);
}
