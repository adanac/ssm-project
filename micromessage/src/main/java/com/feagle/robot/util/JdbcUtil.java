package com.feagle.robot.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Feagle on 2017/6/1.
 */
public class JdbcUtil {
    static Connection connection;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "root");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
