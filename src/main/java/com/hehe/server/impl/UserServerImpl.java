package com.hehe.server.impl;

import com.hehe.dao.LoginDao;
import com.hehe.entity.User;
import com.hehe.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServerImpl <br/>
 * Description: <br/>
 * date: 2019/10/22 15:21<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
@Service
public class UserServerImpl implements UserServer {
    @Autowired
    LoginDao loginDao;

    /**
     * 进行登入检验
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public User login(String username, String password) {
        return loginDao.login(username,password);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public int register(String username, String password) {
        return loginDao.register(username, password);
    }
    @Override
    public int  check(String username){
       return loginDao.check(username);
    }

}
