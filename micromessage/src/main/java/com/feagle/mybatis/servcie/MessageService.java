package com.feagle.mybatis.servcie;

import com.feagle.mybatis.bean.Message;

import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public interface MessageService {
    List<Message> listMessage(String command,String description);
}
