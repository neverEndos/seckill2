package com.endos.seckill.service;

import com.endos.seckill.entity.Seckill;

import java.util.List;

/**
 * Created by Endos on 2017/04/14.
 */
public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(Long seckillId);

    void exportSeckillUrl(Long seckillId);

    void executeSeckill(Long seckillId, Long userPhone, String md5);
}
