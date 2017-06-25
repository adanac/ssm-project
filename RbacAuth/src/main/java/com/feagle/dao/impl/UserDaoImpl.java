package com.feagle.dao.impl;

import com.feagle.common.BaseDao;
import com.feagle.dao.UserDao;
import com.feagle.entity.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.List;


/**
 * Created by Feagle on 2017/6/5.
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("pwd"));
            return user;
        }
    }

    @Override
    public User findUser(String userName, String password) {
        String sql = "select id,name,pwd from auth_user where name = ? and pwd = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{userName, password}, new UserRowMapper());
        return user;
    }

    @Override
    public User findUserById(Long id) {
        String sql = "select id,name,pwd from auth_user where id = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
    }

    @Override
    public Collection<User> findUserByIds(Collection<Long> ids) {
        StringBuilder sb = new StringBuilder("select id,name,pwd from auth_user where id in (");
        ids.forEach((id) -> sb.append(id).append(","));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return jdbcTemplate.query(sb.toString(), new UserRowMapper());
    }

    @Override
    public List<User> findUsers(Collection<Long> ids) {
        StringBuilder sb = new StringBuilder("select id,name,pwd from auth_user where id in (");
        ids.forEach((id) -> sb.append(id).append("?,"));
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return jdbcTemplate.query(sb.toString(), ids.toArray(new Object[0]), new UserRowMapper());
    }

    @Override
    public List<User> listUsers(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        if (size < 0) {
            size = 20;
        }
        String sql = "select id,name,pwd from auth_user limit ?,? ";
        int skip = (page - 1) * size;
        return jdbcTemplate.query(sql, new Object[]{skip, size}, new UserRowMapper());
    }

    @Override
    public Long saveUserReturnKey(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                String sql = "insert into  auth_user(name,pwd) values (?,?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getPassword());
                return ps;
            }
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        return generatedId;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into auth_user(name,pwd) values (?,?)";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update auth_user set name = ? , pwd = ? where id = ? ";
        return jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getId());
    }

    @Override
    public int deleteUserById(Long id) {
        String sql = "delete from auth_user where id = ? ";
        return jdbcTemplate.update(sql, id);
    }


}
