package com.endos.seckill.service;

import com.endos.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Endos on 2017/04/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        for (Seckill seckill : list) {
            System.out.println(seckill);
        }
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void exportSeckillUrl() throws Exception {
    }

    @Test
    public void executeSeckill() throws Exception {
    }

}