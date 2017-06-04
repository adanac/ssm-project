package com.feagle.dao.impl;

import com.feagle.dao.AccountDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 转账dao层的实现类
 * Created by Feagle on 2017/6/4.
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Override
    public void outMoney(String out, Double money) {
        String sql = "update account set money = money - ? where name = ? ";
        this.getJdbcTemplate().update(sql,money,out);
    }

    @Override
    public void inMoney(String in, Double money) {
        String sql = "update account set money = money + ? where name = ? ";
        this.getJdbcTemplate().update(sql,money,in);
    }
}
