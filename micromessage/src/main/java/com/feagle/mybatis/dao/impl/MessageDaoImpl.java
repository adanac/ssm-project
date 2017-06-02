package com.feagle.mybatis.dao.impl;

import com.feagle.mybatis.bean.Message;
import com.feagle.mybatis.dao.MessageDao;
import com.feagle.mybatis.util.DBAccess;
import com.feagle.robot.util.ReturnQuery;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageDaoImpl implements MessageDao {
    public List<Message> listMessage(String command, String description) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);

        String query = ReturnQuery.query(message);
        System.out.println("query in mybatis:"+query);

        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            //通过sqlSession执行sql语句
            //通过namesace.sql的名字执行sql语句。selsct语句就是.select
            messageList = sqlSession.selectList("Message.listMessage", message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
        return messageList;
    }
}
