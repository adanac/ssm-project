package com.feagle.entity;

import com.feagle.dto.BaseEntity;

/**
 * Created by Feagle on 2017/6/5.
 */
public class UserRole extends BaseEntity {
    private Long id;
    private Long userId;
    private Long roleId;

    public UserRole() {
    }

    public UserRole(Long id, Long userId, Long roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long ruleId) {
        this.roleId = ruleId;
    }
}
