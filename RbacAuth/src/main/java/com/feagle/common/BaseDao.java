package com.feagle.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Feagle on 2017/6/5.
 */
@Repository
public class BaseDao {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
