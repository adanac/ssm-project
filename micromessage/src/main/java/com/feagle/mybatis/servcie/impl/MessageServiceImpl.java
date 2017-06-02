package com.feagle.mybatis.servcie.impl;

import com.feagle.mybatis.bean.Message;
import com.feagle.mybatis.dao.MessageDao;
import com.feagle.mybatis.dao.impl.MessageDaoImpl;
import com.feagle.mybatis.servcie.MessageService;

import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageServiceImpl implements MessageService {
    public List<Message> listMessage(String command, String description) {
        MessageDao messageDao = new MessageDaoImpl();
        List<Message> messageList = messageDao.listMessage(command, description);

        return messageList;
    }
}
