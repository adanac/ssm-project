package com.feagle.service;

import org.springframework.stereotype.Service;

/**
 * Created by Feagle on 2017/6/13.
 */
@Service
public class LoginService {
    public boolean login(String username, String password) {
        if ("admin".equals(username) && ("123").equals(password)) {
            return true;
        }
        return false;
    }
}
