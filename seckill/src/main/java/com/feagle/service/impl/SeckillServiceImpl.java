package com.feagle.service.impl;

import com.feagle.dao.SeckillDao;
import com.feagle.dao.SuccessKilledDao;
import com.feagle.dao.cache.RedisDao;
import com.feagle.dto.Exposer;
import com.feagle.dto.SeckillExecution;
import com.feagle.entity.Seckill;
import com.feagle.entity.SuccessKilled;
import com.feagle.enums.SeckillStateEnum;
import com.feagle.exception.RepeatKillException;
import com.feagle.exception.SeckillCloseException;
import com.feagle.exception.SeckillException;
import com.feagle.service.SeckillService;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by feagle on 2017/5/27.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String salt = "2sdaf12!AS&%$";
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;
    @Autowired
    private RedisDao redisDao;

    public List<Seckill> listSeckillList() {
        return seckillDao.queryAll(0, 10);
    }

    public Seckill findSeckillOne(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        //优化点:缓存优化,超时的基础上维护一致性
        //1.访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if(seckill == null){
            //2.访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            }
        }else{
            //3.放入redis中
            redisDao.putSeckill(seckill);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
        }

        //秒杀开启,返回商品id及md5
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 秒杀是否成功，成功:减库存，增加明细；失败:抛出异常，事务回滚
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     * @throws RepeatKillException
     */
    @Transactional
    /**
     * 使用注解控制事务方法的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格
     * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
     * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !getMD5(seckillId).equals(md5)) {
            throw new SeckillException("seckill data rewrite");
        }
        //执行秒杀逻辑:减库存+记录用户购买明细
        try {
            Date killTime = new Date();
            //记录购买行为
            int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {

                //将减库存的操作放在增加购买明细记录的后面,降低了网络延迟和GC影响一倍的时间

                //减库存,热点商品竞争
                int updateCount = seckillDao.reduceNumber(seckillId, killTime);
                if (updateCount <= 0) {
                    //没有更新库存,秒杀结束  rollback
                    throw new SeckillCloseException("seckill is closed");
                } else {
                    //秒杀成功  commit
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw  e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            //将所有编译期异常转换为运行时异常
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }


    /**
     * java客户端调用mysql存储过程执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws SeckillCloseException
     * @throws RepeatKillException
     */
    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)  {
        if (md5 == null || !getMD5(seckillId).equals(md5)) {
            return new SeckillExecution(seckillId,SeckillStateEnum.DATE_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId",seckillId);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);
        try {
            seckillDao.killByProcedure(map);
            //存储过程执行完毕,获取result
            Integer result = MapUtils.getInteger(map, "result", -2);//如果取不到result赋值-2
            if (result == 1) {
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
            } else {
                return new SeckillExecution(seckillId,SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
        }
    }


}
