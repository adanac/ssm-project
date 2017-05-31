package com.feagle.service;

import com.feagle.BaseTest;
import com.feagle.dao.SeckillDao;
import com.feagle.dto.Exposer;
import com.feagle.dto.SeckillExecution;
import com.feagle.entity.Seckill;
import com.feagle.exception.RepeatKillException;
import com.feagle.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by feagle on 2017/5/27.
 */

public class SeckillServiceTest extends BaseTest{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void listSeckillList() throws Exception {
        List<Seckill> seckillList = seckillService.listSeckillList();
        logger.info("seckillList={}", seckillList);
    }

    @Test
    public void findSeckillOne() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = seckillService.findSeckillOne(seckillId);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        logger.info("exposer={}", exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        String md5 = null;
        long userPhone = 124231L;
        long seckillId = 1002L;
        SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
        logger.info("seckillExecution={}", seckillExecution);
    }

    //完整逻辑代码测试，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long userPhone = 13476191876L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                logger.info("seckillExecution={}", seckillExecution);
            } catch (RepeatKillException e) {
                e.printStackTrace();
            } catch (SeckillCloseException e1) {
                e1.printStackTrace();
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void executeSeckillProcedure() throws Exception {
        long seckillId = 1000L;
        long phone = 12612342L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(execution.getSeckillStateEnum().getStateInfo());
        }
    }

}