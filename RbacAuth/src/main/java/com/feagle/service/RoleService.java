package com.feagle.service;

import com.feagle.dao.RoleDao;
import com.feagle.dao.RoleFunctionDao;
import com.feagle.entity.Role;
import com.feagle.entity.RoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
@Service
public class RoleService {
    @Autowired
    private RoleDao roleDAO;
    @Autowired private RoleFunctionDao roleFunctionDAO;

    /**
     * 保存角色信息，同时保存角色对应的功能
     * @param role 角色
     * @param roleFunctions 角色对应的功能（即角色功能的关联关系）
     */
    public List<Long> addRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleDAO.saveRole(role);
        roleFunctions.forEach((rf) -> rf.setRoleId(role.getId()));
        List<Long> longList = roleFunctionDAO.saveRoleFunctionsReturnKey(roleFunctions);
        return longList;
    }

    /**
     * 修改角色信息，同时修改角色对应的功能
     * @param role 角色
     * @param roleFunctions 角色对应的功能（即角色功能的关联关系）
     */
    public void editRole(Role role, Collection<RoleFunction> roleFunctions) {
        roleDAO.updateRole(role);
        roleFunctionDAO.deleteByRoleId(role.getId());
        roleFunctions.forEach((rf) -> rf.setRoleId(role.getId()));
        roleFunctionDAO.saveRoleFunctions(roleFunctions);
    }

    /**
     * 根据 ID 删除角色
     * @param roleId 角色 ID
     */
    public void deleteRole(Long roleId) {
        roleDAO.deleteRole(roleId);
        roleFunctionDAO.deleteByRoleId(roleId);
    }

    /**
     * 分页查询角色信息
     * @param page 当前页码
     * @param size 每页记录数
     * @return 角色集合
     */
    public List<Role> getRoles(int page, int size) {
        List<Role> roles = roleDAO.findRoles(page, size);
        roles.forEach((role) -> {
            List<RoleFunction> roleFunctions = roleFunctionDAO.findRoleFunctionsByRoleId(role.getId());
            StringBuilder functionIds = new StringBuilder();
            roleFunctions.forEach((rf) -> {
                functionIds.append(rf.getFunctionId()).append(",");
            });

            if(functionIds.length() > 1) {
                role.setFunctionIds(functionIds.deleteCharAt(functionIds.length() - 1).toString());
            }
        });
        return roles;
    }

    /**
     * 根据 ID 集合查询角色集合
     * @param ids 角色 ID 集合
     * @return 角色集合
     */
    public List<Role> getRoles(Collection<Long> ids) {
        return roleDAO.findRoles(ids);
    }

    /**
     * 根据角色 ID 查询角色功能对应关系
     * @param roleId 角色 ID
     * @return 角色功能对应关系
     */
    public List<RoleFunction> getRoleFunctions(Long roleId) {
        return roleFunctionDAO.findRoleFunctionsByRoleId(roleId);
    }
}
