package com.feagle.entity;

import com.feagle.dto.BaseEntity;

/**
 * Created by Feagle on 2017/6/5.
 */
public class RoleFunction extends BaseEntity {
    private Long id;
    private Long roleId;
    private Long functionId;
    private int status;

    public RoleFunction() {
    }

    public RoleFunction(Long id, Long ruleId, Long functionId) {
        this.id = id;
        this.roleId = ruleId;
        this.functionId = functionId;
    }

    public RoleFunction(Long id, Long ruleId) {
        this.id = id;
        this.roleId = ruleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
