package com.feagle.dao;

import com.feagle.entity.UserRole;

import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
public interface UserRoleDao {
    /**
     * 根据 ID 查询
     * @param id ID
     * @return 用户角色对应关系
     */
    UserRole fundUserRoleById(Long id);

    /**
     * 保存用户角色对应关系
     * @param userRole 用户角色对应关系
     */
    void saveUserRole(UserRole userRole);
    /**
     * 根据用户 ID 删除用户角色对应关系
     * @param userId 用户 ID
     */
    void deleteByUserId(Long userId);

    /**
     * 分页查询用户角色对应关系
     * @param page 当前页码
     * @param size 每页记录数
     * @return 用户角色对应关系集合
     */
    List<UserRole> findUserRoles(int page, int size);

    /**
     * 根据用户 ID 查询用户角色对应关系
     * @param userId 用户 ID
     * @return
     */
    List<UserRole> findUserRoleByUserId(Long userId);

    /**
     * 保存用户角色对应关系集合
     * @param userRoles 用户角色对应关系集合
     */
    void saveUserRoles(Collection<UserRole> userRoles);
}
