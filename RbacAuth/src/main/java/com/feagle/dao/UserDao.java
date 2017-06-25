package com.feagle.dao;

import com.feagle.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
public interface UserDao {


    /**
     * 根据用户名密码查询用户
     * @param userName
     * @param password
     * @return
     */
    User findUser(String userName,String password);

    /**
     * 查询用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 批量查询用户信息
     */
    Collection<User> findUserByIds(Collection<Long> ids);

    List<User> findUsers(Collection<Long> ids);

    /**
     * 分页查询用户信息
     * @param page
     * @param size
     * @return
     */

    List<User> listUsers(int page,int size);

    /**
     * 保存用户并返回主键
     * @param user
     * @return
     */
    Long saveUserReturnKey(User user);

    /**
     * 保存用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    int deleteUserById(Long id);
}
