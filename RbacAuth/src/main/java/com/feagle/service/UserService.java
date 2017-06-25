package com.feagle.service;

import com.feagle.dao.UserDao;
import com.feagle.dao.UserRoleDao;
import com.feagle.entity.User;
import com.feagle.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDAO;

    /**
     * 保存用户信息
     *
     * @param user 用户
     */
    public Long addUser(User user) {
        int result = userDao.saveUser(user);
        return new Long(result);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户
     */
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 删除用户信息
     *
     * @param id 用户 ID
     */
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    /**
     * 根据用户名、密码查询用户，用于登录
     *
     * @param name 用户名
     * @param pwd  密码
     * @return 用户
     */
    public User getUser(String name, String pwd) {

        try {
            return userDao.findUser(name, pwd);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 分页查询用户信息
     *
     * @param page 当前页码
     * @param size 每页记录数
     * @return 用户集合
     */
    public List<User> getUsers(int page, int size) {
        return userDao.listUsers(page, size);
    }

    /**
     * 根据用户 ID 集合查询用户信息
     *
     * @param ids 用户 ID 集合
     * @return 用户集合
     */
    public List<User> getUsers(Collection<Long> ids) {
        return userDao.findUsers(ids);
    }

    /**
     * 分页查询用户角色对应关系
     *
     * @param page 当前页码
     * @param size 每页记录数
     * @return 用户角色对应关系集合
     */
    public List<UserRole> getUserRoles(Integer page, Integer size) {
        return userRoleDAO.findUserRoles(page, size);
    }

    /**
     * 根据用户 ID 查询用户角色对应关系
     *
     * @param userId 用户 ID
     * @return 用户角色对应关系集合
     */
    public List<UserRole> getUserRolesByUserId(Long userId) {
        return userRoleDAO.findUserRoleByUserId(userId);
    }

    /**
     * 保存用户角色对应关系
     *
     * @param userId  用户 ID
     * @param roleIds 用户对应的角色 ID
     */
    public void addUserRoles(Long userId, Long[] roleIds) {
        List<UserRole> userRoles = new ArrayList<>();

        Arrays.asList(roleIds).forEach((roleId) -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        });
    }
}
