package com.feagle.dto;

import com.feagle.entity.SuccessKilled;
import com.feagle.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后的结果
 * Created by feagle on 2017/5/27.
 */
public class SeckillExecution {
    //id
    private long seckillId;
    //秒杀执行结果状态
    private SeckillStateEnum seckillStateEnum;
    //秒杀成功对象
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.seckillStateEnum = seckillStateEnum;
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.seckillStateEnum = seckillStateEnum;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public SeckillStateEnum getSeckillStateEnum() {
        return seckillStateEnum;
    }

    public void setSeckillStateEnum(SeckillStateEnum seckillStateEnum) {
        this.seckillStateEnum = seckillStateEnum;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
