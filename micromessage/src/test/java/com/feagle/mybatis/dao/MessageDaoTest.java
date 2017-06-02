package com.feagle.mybatis.dao;

import com.feagle.mybatis.bean.Message;
import com.feagle.mybatis.dao.impl.MessageDaoImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageDaoTest {
    /**
     * java.lang.NoClassDefFoundError: com/feagle/mybatis/dao/MessageDao
     * @throws Exception
     */
    @Test
    public void listMessage() throws Exception {
        MessageDao messageDao = new MessageDaoImpl();
        String command = "";
        String description = "";
        List<Message> messageList = messageDao.listMessage(command, description);
        for(Message message:messageList){
            System.out.println(message.toString());
        }
    }

}