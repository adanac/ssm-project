package com.feagle.robot.dao.impl;

import com.feagle.robot.bean.Message;
import com.feagle.robot.dao.MessageDao;
import com.feagle.robot.util.CommonUtil;
import com.feagle.robot.util.JdbcUtil;
import com.feagle.robot.util.ReturnQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by Feagle on 2017/6/1.
 */
public class MessageDaoImpl implements MessageDao {

    public List<Message> listMessage(String command, String description) {
        List<Message> messageList = new ArrayList<Message>();
        Map<String, String> pmap = new HashMap<String, String>();//存放参数的map
        Message messageParam = new Message();//打印sql参数
        try {
            Connection connection = JdbcUtil.getConnection();
            StringBuilder sql = new StringBuilder("select id,command,description,content from message where 1=1 ");
            //因为有条件查询，但是statement在有条件查询之前就创建好了，这样如果再有了条件没法拼接sql语句
            //所以设一个List来缓存sql语句，然后拼接进statement中
            List<String> paramList = new ArrayList<String>();

            //把条件拼到statement中
            if (command != null && !"".equals(command.trim())){
                sql.append(" and command = ? ");
                paramList.add(command);
                messageParam.setCommand(command);
            }
            if (description != null && !"".equals(description.trim())){
                sql.append(" and description like '%' ? '%'");
                paramList.add(description);
                messageParam.setDescription(description);
            }
            String query = ReturnQuery.query(messageParam);
            System.out.println("query in jdbc:"+query);

            PreparedStatement statement = connection.prepareStatement(sql.toString());
            //把条件拼到statement中
            for (int i = 0; i < paramList.size(); i++){
                statement.setString(i + 1, paramList.get(i));
            }



            ResultSet resultSet = statement.executeQuery();
            //取出来是多组数据，再单独建立一个与数据库表对应的java bean。bean的包叫bean。
            //然后把取出的值放入到list里面
            while (resultSet.next()) {
                Message message = new Message();
                messageList.add(message);//添加的是对象的引用
                message.setId(resultSet.getString("id"));
                message.setCommand(resultSet.getString("command"));
                message.setDescription(resultSet.getString("description"));
                message.setContent(resultSet.getString("content"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
