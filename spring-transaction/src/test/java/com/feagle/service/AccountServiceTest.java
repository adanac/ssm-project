package com.feagle.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 转账业务层测试类
 * Created by Feagle on 2017/6/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class AccountServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AccountService accountService;
    @Test
    public void transfer() throws Exception {
        Double money = 200d;
        String out = "aaa";
        String in = "bbb";
        try {

            accountService.transfer(out,in,money);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}