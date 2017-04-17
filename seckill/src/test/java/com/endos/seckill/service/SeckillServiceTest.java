package com.endos.seckill.service;

import com.endos.seckill.dto.Exposer;
import com.endos.seckill.dto.SeckillExecution;
import com.endos.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Endos on 2017/04/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml" })
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
        Seckill seckill = seckillService.getById(1000L);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1002L);
        System.out.println(exposer);
        // Exposer{exposed=true, md5='ac3cf9b23a6f2a538ef3845e4b806938', seckillId=1002, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() throws Exception {
        SeckillExecution seckillExecution = seckillService.executeSeckill(1002L, 13814860939L, "ac3cf9b23a6f2a538ef3845e4b806938");
        System.out.println(seckillExecution);
    }

}