package com.feagle.robot.servcie;

import com.feagle.robot.bean.Message;

import java.util.List;

/**
 * Created by Feagle on 2017/6/1.
 */
public interface MessageService {
    /**
     * 查询消息列表
     * @param command
     * @param description
     * @return
     */
    List<Message> listMessage(String command, String description);
}
