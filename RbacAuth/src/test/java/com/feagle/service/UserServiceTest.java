package com.feagle.service;

import com.feagle.entity.Role;
import com.feagle.entity.RoleFunction;
import com.feagle.entity.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Feagle on 2017/6/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-applicationContext.xml")
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUserName("adanac");
        user.setPassword("123");
        Long result = userService.addUser(user);
        logger.info("result={}",result);
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUserById() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
        User user = userService.getUser("allen", "123d");
        logger.info("user={}", user);

    }

    @Test
    public void getUsers() throws Exception {
    }

    @Test
    public void getUsers1() throws Exception {
    }

    @Test
    public void getUserRoles() throws Exception {
    }

    @Test
    public void getUserRolesByUserId() throws Exception {
    }

    @Test
    public void addUserRoles() throws Exception {
        User user = new User("china","1234");
        Long userId = userService.addUser(user);
        Role role = new Role();
        role.setId(1001L);
        role.setName("meeting");
        Collection<RoleFunction> functions = new ArrayList<RoleFunction>();
        RoleFunction roleFunction = new RoleFunction();
        roleFunction.setRoleId(1001L);
        functions.add(roleFunction);
        List<Long> longList = roleService.addRole(role, functions);
        userService.addUserRoles(userId,(Long[])longList.toArray());

    }

}