package com.feagle.dao;

import com.feagle.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
public interface RoleDao {
    /**
     * 根据 ID 查询角色
     * @param id 角色 ID
     * @return 角色对象
     */
    Role findRoleById(Long id);

    /**
     * 保存角色
     * @param role 角色对象
     */
    void saveRole(Role role);

    /**
     * 根据 ID 删除角色
     * @param roleId 角色 ID
     */
    void deleteRole(Long roleId);

    /**
     * 更新角色
     * @param role 角色
     */
    void updateRole(Role role);

    /**
     * 根据 ID 集合批量查询
     * @param ids 角色 ID 集合
     * @return 角色集合
     */
    List<Role> findRoles(Collection<Long> ids);

    /**
     * 分页查询角色信息
     * @param page 当前页码
     * @param size 每页记录数
     * @return 角色集合
     */
    List<Role> findRoles(int page, int size);
}
