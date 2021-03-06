package com.feagle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * 转账业务层测试类
 * Created by Feagle on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void transfer() {
        Double money = 200d;
        String out = "aaa";
        String in = "bbb";
        accountService.transfer(out, in, money);
    }

}