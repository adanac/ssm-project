package com.feagle.service;

import com.feagle.exception.MyException;

/**
 * Created by Feagle on 2017/6/11.
 */
public class ServletService {
    public void add() {
        System.out.println("add() was called!!");
        if (true) {
            throw new MyException("in add() throws Exception!!");//抛出异常，根据业务逻辑
        }
    }

}
