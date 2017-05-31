package com.feagle.service;

import com.feagle.dto.Exposer;
import com.feagle.dto.SeckillExecution;
import com.feagle.entity.Seckill;
import com.feagle.exception.RepeatKillException;
import com.feagle.exception.SeckillCloseException;
import com.feagle.exception.SeckillException;

import java.util.List;

/**
 * 业务接口:站在使用者(coder)的角度设计接口
 * Created by feagle on 2017/5/27.
 */
public interface SeckillService {

    /**
     * 查询所有的秒杀记录
     *
     * @return
     */
    List<Seckill> listSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill findSeckillOne(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址,否则输出系统时间 和 秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)throws SeckillException,SeckillCloseException,RepeatKillException;

    /**
     * 执行秒杀by存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     * @throws RepeatKillException
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
