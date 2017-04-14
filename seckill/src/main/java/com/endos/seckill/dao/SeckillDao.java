package com.endos.seckill.dao;

import com.endos.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Endos on 2017/04/14.
 */
public interface SeckillDao {

    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(@Param("id") Long id);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
