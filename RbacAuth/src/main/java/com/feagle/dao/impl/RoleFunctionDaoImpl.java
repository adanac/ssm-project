package com.feagle.dao.impl;

import com.feagle.common.BaseDao;
import com.feagle.dao.RoleFunctionDao;
import com.feagle.entity.RoleFunction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Feagle on 2017/6/5.
 */
@Repository
public class RoleFunctionDaoImpl extends BaseDao implements RoleFunctionDao {

    private class RoleFunctionMapper implements RowMapper<RoleFunction> {

        @Override
        public RoleFunction mapRow(ResultSet rs, int rowNum) throws SQLException {
            RoleFunction roleFunction = new RoleFunction();
            roleFunction.setId(rs.getLong("id"));
            roleFunction.setRoleId(rs.getLong("role_id"));
            roleFunction.setFunctionId(rs.getLong("function_id"));
            roleFunction.setStatus(rs.getInt("status"));
            return roleFunction;
        }
    }

    /**
     * 根据 ID 查询角色功能对应关系
     *
     * @param id 角色功能对应关系 ID
     * @return 角色功能对应关系
     */
    public RoleFunction findRoleFunctionById(Long id) {
        String sql = "select * from auth_role_function where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RoleFunctionMapper());
    }

    @Override
    public List<Long> saveRoleFunctionsReturnKey(Collection<RoleFunction> roleFunctions) {
        List<Long> idList = new ArrayList<Long>();
        String sql = "insert into auth_role_function(role_id, function_id, status) values (?, ?, ?)";
        try {
            Connection connection = this.jdbcTemplate.getDataSource().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            List<Object[]> batchArgs = new ArrayList<>();


            for (RoleFunction rf :
                    roleFunctions) {
                pstmt.setLong(1, rf.getRoleId());
                pstmt.setLong(2, rf.getFunctionId());
                pstmt.setInt(3, rf.getStatus());
            }
            pstmt.executeBatch();
            connection.commit();
            ResultSet keys = pstmt.getGeneratedKeys();//获取结果

            while (keys.next()) {
                idList.add(keys.getLong(1));//取得ID
            }
            connection.close();
            pstmt.close();
            keys.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idList;
    }

    /**
     * 保存角色功能对应关系集合
     *
     * @param roleFunction 角色功能对应关系集合
     */
    public void saveRoleFunctions(Collection<RoleFunction> roleFunction) {
        String sql = "insert into auth_role_function(role_id, function_id, status) values (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        roleFunction.forEach((rf) -> {
            Object[] objs = new Object[3];
            objs[0] = rf.getRoleId();
            objs[1] = rf.getFunctionId();
            objs[2] = rf.getStatus();

            batchArgs.add(objs);
        });
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    /**
     * 根据角色 ID 查询角色功能对应关系集合
     *
     * @param roleId 角色 ID
     * @return 角色功能对应关系集合
     */
    public List<RoleFunction> findRoleFunctionsByRoleId(Long roleId) {
        String sql = "select * from auth_role_function where role_id = ?";
        return jdbcTemplate.query(sql, new Object[]{roleId}, new RoleFunctionMapper());
    }

    /**
     * 根据角色 ID 删除角色功能对应关系集合
     *
     * @param roleId 角色 ID
     */
    public void deleteByRoleId(Long roleId) {
        String sql = "delete from auth_role_function where role_id = ?";
        jdbcTemplate.update(sql, roleId);
    }
}
