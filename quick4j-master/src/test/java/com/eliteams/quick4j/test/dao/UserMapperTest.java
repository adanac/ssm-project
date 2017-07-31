package com.eliteams.quick4j.test.dao;

import java.util.List;
import javax.annotation.Resource;

import org.junit.Test;
import com.eliteams.quick4j.core.feature.orm.mybatis.Page;
import com.eliteams.quick4j.test.TestSupport;
import com.eliteams.quick4j.web.dao.UserMapper;
import com.eliteams.quick4j.web.model.User;
import com.eliteams.quick4j.web.model.UserExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserMapperTest extends TestSupport {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    @Test
    public void test_selectByExampleAndPage() {
        start();
        Page<User> page = new Page<>(1, 3);
        UserExample example = new UserExample();
        example.createCriteria().andIdGreaterThan(0L);
        final List<User> users = userMapper.selectByExampleAndPage(page, example);
        for (User user : users) {
            System.err.println(user);
        }
        end();
    }
}
