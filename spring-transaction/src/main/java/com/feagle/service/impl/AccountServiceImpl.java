package com.feagle.service.impl;

import com.feagle.dao.AccountDao;
import com.feagle.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by Feagle on 2017/6/4.
 */
@Transactional
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入转账的DAO类
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void transfer(String out, String in, Double money) {
        logger.info("transfer...");
        try {
            accountDao.outMoney(out, money);
            int i = 1 / 0;
            accountDao.inMoney(in, money);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }
}
