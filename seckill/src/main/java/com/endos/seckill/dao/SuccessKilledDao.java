package com.endos.seckill.dao;

import com.endos.seckill.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * 成功秒杀Dao
 * Created by Endos on 2017/04/14.
 */
public interface SuccessKilledDao {
    int insertSuccessSeckilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}
