package com.endos.seckill.dao;

import com.endos.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Endos on 2017/04/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessSeckilled() throws Exception {
        int result = successKilledDao.insertSuccessSeckilled(1001L,15250452668L);
        System.out.println(result);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1001L, 17542546987L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}