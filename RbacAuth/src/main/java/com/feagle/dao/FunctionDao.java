package com.feagle.dao;

import com.feagle.entity.Function;

import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
public interface FunctionDao {
    /**
     * 保存功能
     * @param function 功能对象
     */
    void saveFunction(Function function);

    /**
     * 根据 ID 更新功能的 URL
     * @param id 功能 ID
     * @param url URL
     */
    void updateUrl(Long id, String url);

    /**
     * 根据 ID 删除功能
     * @param id 功能 ID
     */
    void deleteById(Long id);

    /**
     * 分页查询功能（根据父节点）
     * @param page 当前页码
     * @param size 每页记录数
     * @param parentId 父节点 ID
     * @return 功能集合
     */
    List<Function> findFunctions(int page, int size, Long parentId);

    /**
     * 查询全部功能
     * @return 功能集合
     */
    List<Function> getAllFunctions();
}
