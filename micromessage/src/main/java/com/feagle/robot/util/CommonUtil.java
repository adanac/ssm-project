package com.feagle.robot.util;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * Created by Feagle on 2017/6/1.
 */
public class CommonUtil {
    public static final Logger logger = Logger.getLogger(CommonUtil.class);
    /**
     * 显示SQL语句
     */
    public static void show_sql(String sql, Map<String,String> pmap) {
        logger.info("##sql:{}"+sql);
        logger.info("##参数:{}"+JSON.toJSONString(pmap));
    }
}
