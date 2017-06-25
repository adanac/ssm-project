package com.feagle.dao;

import com.alibaba.fastjson.JSON;
import com.feagle.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * UserDao的测试用例
 * Created by Feagle on 2017/6/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationContext.xml")
public class UserDaoTest {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Test
    public void findUser() throws Exception {
        User user = userDao.findUser("allen", "123");
        logger.info("user={}", JSON.toJSONString(user));
        assertNotNull(user);
    }

    @Test
    public void findUserById() throws Exception {
        User user = userDao.findUserById(1L);
        logger.info("user={}", JSON.toJSONString(user));
        assertNotNull(user);
    }

    @Test
    public void findUserByIds() throws Exception {

        Collection<User> users = userDao.findUserByIds(Arrays.asList(1L, 2L));
        for (User user : users) {
            logger.info("user={}", user);
        }
        assertTrue(users.size() > 0);

    }


    @Test
    public void listUsers() throws Exception {
        List<User> users = userDao.listUsers(1, 10);
        logger.info("users={}", JSON.toJSONString(users));
        assertTrue(users.size() > 0);
    }


    @Test
    public void saveUserReturnKey() throws Exception {
        User user = new User("scott", "1234");
        Long id = userDao.saveUserReturnKey(user);
        logger.info("id={}", id);
        assertTrue(id > 0);
    }

    @Test
    public void saveUser() throws Exception {
        User user = new User("scott", "1234");
        int result = userDao.saveUser(user);
        assertTrue(result > 0);
    }

    @Test
    public void updateUser() throws Exception {
        User user = userDao.findUserById(3L);
        user.setPassword("456");
        int result = userDao.updateUser(user);
        assertTrue(result > 0);
    }

    @Test
    public void deleteUserById() throws Exception {
        int result = userDao.deleteUserById(3L);
        assertTrue(result > 0);
    }

}