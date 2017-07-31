package com.eliteams.quick4j.test.service;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.eliteams.quick4j.core.util.ApplicationUtils;
import com.eliteams.quick4j.test.TestSupport;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserExample;
import com.eliteams.quick4j.web.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest extends TestSupport {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @Test
    public void test_insert() {
        User model = new User();
        model.setUsername("adanac");
        model.setPassword(ApplicationUtils.sha256Hex("123456"));
        model.setCreateTime(new Date());
        userService.insert(model);
    }

    @Test
    public void test_10insert() {
        for (int i = 0; i < 10; i++) {
            User model = new User();
            model.setUsername("lfz" + i);
            model.setPassword(ApplicationUtils.sha256Hex("123456"));
            model.setCreateTime(new Date());
            userService.insert(model);
        }
    }

    @Test
    public void test_selectList(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andCreateTimeIsNotNull();
        List<User> users = userService.listUser(userExample);
        LOGGER.info("users is {}", JSON.toJSONString(users));
    }

}
