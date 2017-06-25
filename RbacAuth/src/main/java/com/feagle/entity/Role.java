package com.feagle.entity;

import com.feagle.dto.BaseEntity;

/**
 * Created by Feagle on 2017/6/5.
 */
public class Role extends BaseEntity {
    private Long id;
    private String name;
    private String functionIds;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
}
