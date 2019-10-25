package com.hehe.dao;
import	java.security.Provider.Service;

import com.hehe.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * ClassName: LoginDao <br/>
 * Description: <br/>
 * date: 2019/10/22 9:28<br/>
 *
 * @author me<br />
 * @since JDK 1.8
 */
@Component

public interface LoginDao {
    @Select("select username from sys_user where username=#{username} and password= #{password}")
    public User login(String username,String password);
    @Insert("insert into sys_user(username,password) VALUES (#{username},#{password})")
    public int  register(String username, String password);
    @Select("select count(0) from sys_user where username=#{username}")
    public int  check(String username);
}
