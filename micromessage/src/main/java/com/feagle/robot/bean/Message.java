package com.feagle.robot.bean;

import com.feagle.robot.annotation.Column;
import com.feagle.robot.annotation.Table;

/**
 * 与消息表对应的实体
 * Created by Feagle on 2017/6/1.
 */
@Table("message")
public class Message {
    /**
     * 主键
     */
    @Column("id")
    private String id;
    /**
     * 指令名称
     */
    @Column("command")
    private String command;
    /**
     * 描述
     */
    @Column("description")
    private String description;
    /**
     * 内容
     */
    @Column("content")
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
