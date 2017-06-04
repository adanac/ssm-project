package com.feagle.dao;

/**
 * 转账Dao层接口
 *
 * Created by Feagle on 2017/6/4.
 */
public interface AccountDao {
    /**
     *
     * @param out   :转出账号
     * @param money :转出金额
     */
    void outMoney(String out,Double money);

    /**
     *
     * @param in 转入账号
     * @param money 转入金额
     */
    void inMoney(String in,Double money);
}
