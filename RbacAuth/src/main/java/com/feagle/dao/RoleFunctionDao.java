package com.feagle.dao;

import com.feagle.entity.RoleFunction;

import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
public interface RoleFunctionDao {

    /**
     * 根据 ID 查询角色功能对应关系
     * @param id 角色功能对应关系 ID
     * @return 角色功能对应关系
     */
    public RoleFunction findRoleFunctionById(Long id);

    /**
     * 批量保存角色功能对应关系集合并返回主键集合
     * @param roleFunction
     * @return
     */
    List<Long> saveRoleFunctionsReturnKey(Collection<RoleFunction> roleFunction);

    /**
     * 保存角色功能对应关系集合
     * @param roleFunction 角色功能对应关系集合
     */
    void saveRoleFunctions(Collection<RoleFunction> roleFunction);

    /**
     * 根据角色 ID 查询角色功能对应关系集合
     * @param roleId 角色 ID
     * @return 角色功能对应关系集合
     */
    List<RoleFunction> findRoleFunctionsByRoleId(Long roleId);

    /**
     * 根据角色 ID 删除角色功能对应关系集合
     * @param roleId 角色 ID
     */
    void deleteByRoleId(Long roleId);
}
