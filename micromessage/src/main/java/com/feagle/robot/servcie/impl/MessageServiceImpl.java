package com.feagle.robot.servcie.impl;

import com.feagle.robot.dao.MessageDao;
import com.feagle.robot.dao.impl.MessageDaoImpl;
import com.feagle.robot.servcie.MessageService;
import com.feagle.robot.util.ReturnQuery;

import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageServiceImpl implements MessageService {
    public List<com.feagle.robot.bean.Message> listMessage(String command, String description) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.listMessage(command, description);
    }
}
