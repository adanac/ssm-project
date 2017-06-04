package com.feagle.service;

/**
 * 转账业务层接口
 * Created by Feagle on 2017/6/4.
 */
public interface AccountService {

    /**
     *
     * @param out   :转出账号
     * @param in    :转入账号
     * @param money :转账金额
     */
    void transfer(String out,String in,Double money);
}
