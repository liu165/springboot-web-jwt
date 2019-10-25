package com.hehe.server;

import com.hehe.entity.User;

/**
 * ClassName: UserServer <br/>
 * Description: <br/>
 * date: 2019/10/22 15:20<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
public interface UserServer {
    public User login(String username, String password);
    public int  register(String username, String password);
    public int  check(String username);
}
